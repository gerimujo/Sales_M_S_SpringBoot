package com.bar.barproject.Controller;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bar.barproject.Adminhyrreprository.adminhyr;
import com.bar.barproject.Adminhyrreprository.adminhyrRep;
import com.bar.barproject.ReprositoryAdmin.AdminRep;
import com.bar.barproject.ReprositoryAdmin.admin;
import com.bar.barproject.ReprositoryDrinks.DrinkRep;
import com.bar.barproject.ReprositoryDrinks.drinks;
import com.bar.barproject.ReprositoryWaiter.waiter;
import com.bar.barproject.orderRep.orderRep;
import com.bar.barproject.orderRep.orders;
import com.bar.barproject.waiterhyrReporsitory.waiterhyr;
import com.bar.barproject.waiterhyrReporsitory.waiterhyrRep;
import com.bar.barproject.ReprositoryWaiter.WaiterRep;


@RestController
@RequestMapping(value="/rest")
public class cont {
    @Autowired
     private  DrinkRep drinkRep;
     @Autowired
     private WaiterRep waiterRep;
     @Autowired
     private orderRep orderRep;
     @Autowired
     private waiterhyrRep waiterhyrRep;
     @Autowired
     private AdminRep adminRep;
     @Autowired
     private adminhyrRep adminhyrRep;
     public void mycontroller6(adminhyrRep adminhyrRep){
        this.adminhyrRep =adminhyrRep;
     }
   public void mycontroller5(AdminRep adminRep){
    this.adminRep = adminRep;
   }

     public void mycontroller4(waiterhyrRep waiterhyrRep){
        this.waiterhyrRep = waiterhyrRep;
     }

     public void mycontroller3(orderRep orderRep){
        this.orderRep = orderRep;
     }

    public void  mycontroller(DrinkRep drinkRep) {
        this.drinkRep = drinkRep;
    }
    public void mycontroller1(WaiterRep waiterRep){
        this.waiterRep= waiterRep;
    }


    @PostMapping(value="/addDrinks")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public List<drinks> post(@RequestBody final drinks drink){
        drinkRep.save(drink);
        return drinkRep.findAll();
    } 
    @GetMapping(value="/getDrinks/{id}")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public List<drinks> get(@PathVariable Integer id){
        List<adminhyr> datahyr = adminhyrRep.findAll();
        if(datahyr.size()!=0&&datahyr.get(0).getId()==id){
            return drinkRep.findAll();
        }else{
            List<drinks> dd =  new ArrayList<>();
            drinks ff = new drinks();
            
            ff.setId(-1);
            ff.setName("null");
            ff.setPrice(0);
            ff.setQuantity(0);
            dd.add(0, ff);
            return dd;


        }
    }
    @GetMapping(value="/getDrinks1/{id}")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public List<drinks> get1(@PathVariable Integer id){
        List<waiterhyr> datahyr = waiterhyrRep.findAll();
        if(datahyr.size()!=0&&datahyr.get(0).getId()==id){
            return drinkRep.findAll();
        }else{
            List<drinks> dd =  new ArrayList<>();
            drinks ff = new drinks();
            
            ff.setId(-1);
            ff.setName("null");
            ff.setPrice(0);
            ff.setQuantity(0);
            dd.add(0, ff);
            return dd;


        }
    }
    @DeleteMapping(value="/deletedrink")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public List<drinks> delete(@RequestParam Integer id){
    drinkRep.deleteById(id);
    return drinkRep.findAll();
    
}
@PutMapping(value="/updateDrink")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<drinks> update(@RequestBody  drinks drink){
    Integer number = drink.getId();
    drinks data =  drinkRep.findById(number).get();
    data.setName(drink.getName());
    data.setPrice(drink.getPrice());
    data.setQuantity(drink.getQuantity());
 drinkRep.save(data);
 return drinkRep.findAll();


}
@PostMapping(value="/addWaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<waiter> addwaiter(@RequestBody final waiter waiter){
    waiterRep.save(waiter);
    return waiterRep.findAll();
}
@GetMapping(value="/getWaiter/{id}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<waiter> getwait(@PathVariable Integer id){
   
   List<adminhyr> datahyr = adminhyrRep.findAll();
   if(datahyr.size()!=0&&datahyr.get(0).getId()==id){
    List<waiter> wt = waiterRep.findAll();
    for(int i =0; i<wt.size(); i++){
        wt.get(i).setDisplay("none");;
    }

    return wt;
   }
   else{
    List<waiter> wt1 = new ArrayList();
    waiter wt2 =  new waiter();
    wt2.setId(-1);
    wt2.setName("null");
    wt2.setName("null");
    wt1.add(0, wt2);
    return wt1;
   }
}
@DeleteMapping(value="/deletewaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<waiter> deletewaiter(@RequestParam Integer id){
waiterRep.deleteById(id);
return waiterRep.findAll();
}
@PutMapping("/updatawaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<waiter> updatewaiter(@RequestBody waiter waiter){
Integer id = waiter.getId();
List<orders> data2 =  orderRep.findByIduser(waiter.getId());
for(int i =0; i<data2.size();i++){
    data2.get(i).setName(waiter.getName());
}

waiter data = waiterRep.findById(id).get();
data.setName(waiter.getName());
data.setPassword(waiter.getPassword());
waiterRep.save(data);

    return waiterRep.findAll();
}
@PutMapping(value="/updateDrinksorder")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<drinks> updateDrinks(@RequestBody List<drinks> drinks){
   
for(int i = 0; i<drinks.size();i++){
    Integer q = drinks.get(i).getId();
    drinks ss = drinkRep.findById(q).get();
    
    ss.setQuantity(drinks.get(i).getQuantity());
    drinkRep.save(ss);
}
return drinkRep.findAll();
}

@PostMapping(value="/addorder")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<orders> addorder(@RequestBody List<orders> orderList){
     List<waiterhyr> datawaiterhyr = waiterhyrRep.findAll();
    List<orders> dataorder = orderRep.findByIduser(datawaiterhyr.get(0).getId());

   
    if(dataorder.size()==0){
        for(int j =0; j<=orderList.size(); j++){
            orderList.get(j).setIduser(datawaiterhyr.get(0).getId());
            orderList.get(j).setName(datawaiterhyr.get(0).getName());
            orderList.get(j).setPrice(orderList.get(j).getPrice()*orderList.get(j).getQuatity());
          // orderList.get(j).setPrice(200);
           orderList.get(j).setDay(1);
            orderList.get(j).setDayaktiv(0);
          
                  orderRep.save(orderList.get(j));

        }
           return orderRep.findAll();

    }else {
        for(int j =0; j<=orderList.size()-1; j++){
            orderList.get(j).setIduser(datawaiterhyr.get(0).getId());
            orderList.get(j).setName(datawaiterhyr.get(0).getName());
            orderList.get(j).setPrice(orderList.get(j).getPrice()*orderList.get(j).getQuatity());
       //    orderList.get(j).setPrice(300);
           int day=0;
         int dayaktiv=0;
         if(dataorder.get(dataorder.size()-1).getDayaktiv()==0){
            day=dataorder.get(dataorder.size()-1).getDay();
            dayaktiv =0;
         }
         else if(dataorder.get(dataorder.size()-1).getDayaktiv()==1){
            day=dataorder.get(dataorder.size()-1).getDay()+1;
            dayaktiv =0;
         }

           orderList.get(j).setDay(day);
            orderList.get(j).setDayaktiv(dayaktiv);
         

                  orderRep.save(orderList.get(j));
        }
        return orderRep.findAll();

    }
    //return orderRep.findAll();
}
@GetMapping(value="/getdays/{name}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public Integer getwaiterhyr1(@PathVariable String name){
List<waiterhyr> datahyr = waiterhyrRep.findAll();

if(datahyr.size()!=0&&datahyr.get(0).getName().equals(name)){
    List<waiterhyr> data  = waiterhyrRep.findAll();
    Integer id = data.get(0).getId();
    List<orders> ord =  orderRep.findByIduser(id);
 
    return ord.get(ord.size()-1).getDay();
}else{
    return (-1);
}
  
}


@GetMapping(value="/getdays1/{id}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public Integer getwaiterhyr11(@PathVariable Integer id){
List<waiterhyr> datahyr = waiterhyrRep.findAll();

if(datahyr.size()!=0&&datahyr.get(0).getId().equals(id)){
    List<waiterhyr> data  = waiterhyrRep.findAll();
    Integer id1 = data.get(0).getId();
    List<orders> ord =  orderRep.findByIduser(id1);
 
    return ord.get(ord.size()-1).getDay();
}else{
    return (-1);
}
  
}
@GetMapping(value="/allorders")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<orders> getorders(){
    List<waiterhyr> data  = waiterhyrRep.findAll();
    Integer id = data.get(0).getId();
    List<orders> ord =  orderRep.findByIduser(id);

return ord;
}
@PutMapping(value="/updatestateday")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<orders> updatestate(){
    List<waiterhyr> waiter =  waiterhyrRep.findAll();
    List<orders> order =  orderRep.findByIduser(waiter.get(0).getId());
    for(int i =0; i<order.size();i++){
        order.get(i).setDayaktiv(1);
        orderRep.save(order.get(i));
    }
    return orderRep.findAll();
}
@GetMapping(value="/getwaitershistory/{id}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<String> getwaiters(@PathVariable Integer id){
  List<adminhyr> datahyr =  adminhyrRep.findAll();
  if(datahyr.size()!=0&&datahyr.get(0).getId()==id){
    List<String> data = orderRep.findDistinctColumnValues();
    return data;
  }else{
    List<String> data1 = new ArrayList();
    data1.add("No");
    return data1;
  }
  
   
}
@PostMapping(value="/openwaiterhistory")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public  List<waiterhyr>  getday(@RequestBody waiterhyr datas){

    List<waiter> datawaiter = waiterRep.findByName(datas.getName());
   waiterhyrRep.deleteAll();
   waiterhyr hyr =  new waiterhyr();
   hyr.setId(datawaiter.get(0).getId());
   hyr.setName(datawaiter.get(0).getName());
   hyr.setPassword(datawaiter.get(0).getPassword());
   waiterhyrRep.save(hyr);
   return waiterhyrRep.findAll();

}
@PostMapping(value="/loginadmin")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<String> loginadmin(@RequestBody admin data){
    List<String> response =  new ArrayList();
List<admin> data1 = adminRep.findByUser(data.getUser());
    if(data1.size()!=0&&data.getPassword().equals(data1.get(0).getPassword())){
        response.add("Ok");
        adminhyrRep.deleteAll();
        adminhyr datahyr = new adminhyr();
        datahyr.setId(data1.get(0).getId());
        datahyr.setUser(data1.get(0).getUser());
        datahyr.setPassoword(data1.get(0).getPassword());
      adminhyrRep.save(datahyr);
        response.add(data1.get(0).getId().toString());
        return response;
    }else{
        response.add("Data is not correct");
        return response;
    }

  
}
@PutMapping(value="/loginwaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<String> loginwaiter(@RequestBody waiter data){
List<waiter> data1 =  waiterRep.findByName(data.getName());
List<String> response =  new ArrayList();
if(data1.size()!=0&&data1.get(0).getPassword().equals(data.getPassword())){
  List<waiterhyr> data2 = waiterhyrRep.findAll();
  waiterhyr data3 = new waiterhyr();
waiterhyrRep.deleteAll();

  data3.setId(data1.get(0).getId());
data3.setName(data1.get(0).getName());
data3.setPassword(data1.get(0).getPassword());
  
  waiterhyrRep.save(data3);
    response.add("Ok");
    response.add(data1.get(0).getId().toString());
    return response;
}else{
    response.add("Data is not correct");
    return response;


}



}
@DeleteMapping(value="/deleteaccount")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public String  deleteacc(){
    waiterhyrRep.deleteAll();
    adminhyrRep.deleteAll();
    return "Done";
}
@GetMapping(value="/prov")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<orders> getMethodName(@RequestParam String name) {
    return orderRep.findByName1(name);}




}

