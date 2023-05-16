package com.bar.barproject.Controller;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
   //  private waiterRep waiterRep
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
    @GetMapping(value="/getDrinks")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public List<drinks> get(){
        return drinkRep.findAll();
       
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
@GetMapping(value="/getWaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<waiter> getwait(){
    List<waiter> wt = waiterRep.findAll();
    for(int i =0; i<wt.size(); i++){
        wt.get(i).setDisplay("none");;
    }

    return wt;
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
@GetMapping(value="/getdays")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public Integer getwaiterhyr1(){
   List<waiterhyr> data  = waiterhyrRep.findAll();
   Integer id = data.get(0).getId();
   List<orders> ord =  orderRep.findByIduser(id);

   return ord.get(ord.size()-1).getDay();
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
@GetMapping(value="/getwaitershistory")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<String> getwaiters(){
   List<String> data = orderRep.findDistinctColumnValues();
   return data;
}
@PutMapping(value="/openwaiterhistory")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public  List<waiterhyr>  getday(@RequestBody List<waiterhyr> datas){

    List<waiter> datawaiter = waiterRep.findByName(datas.get(0).getName());
    List<waiterhyr> datawaiterhyr = waiterhyrRep.findAll();
    datawaiterhyr.get(0).setId(datawaiter.get(0).getId());
    datawaiterhyr.get(0).setName(datas.get(0).getName());
    datawaiterhyr.get(0).setPassword(datawaiter.get(0).getPassword());

    return waiterhyrRep.findAll();


}
@PostMapping(value="/loginadmin")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public List<String> loginadmin(@RequestBody admin data){
    List<String> response =  new ArrayList();
List<admin> data1 = adminRep.findByUser(data.getUser());
    if(data.getPassword().equals(data1.get(0).getPassword())){
        response.add("Ok");
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
if(data1.get(0).getPassword().equals(data.getPassword())){
  List<waiterhyr> data2 = waiterhyrRep.findAll();
  waiterhyr data3 = new waiterhyr();
waiterhyrRep.deleteAll();
  data2.get(0).setId(data1.get(0).getId());
  data2.get(0).setName(data1.get(0).getName());
  data2.get(0).setPassword(data1.get(0).getPassword());
  data3.setId(data1.get(0).getId());
data3.setName(data1.get(0).getName());
data3.setPassword(data1.get(0).getPassword());
  
  waiterhyrRep.save(data3);
    response.add("Ok");
    return response;
}else{
    response.add("Data is not correct");
    return response;


}



}




}
