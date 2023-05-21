package com.bar.barproject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bar.barproject.Model.admin;
import com.bar.barproject.Model.adminhyr;
import com.bar.barproject.Model.waiter;

import com.bar.barproject.Model.drinks;
import com.bar.barproject.Model.orders;
import com.bar.barproject.Model.waiterhyr;
import com.bar.barproject.Reprository.AdminRep;
import com.bar.barproject.Reprository.DrinkRep;
import com.bar.barproject.Reprository.WaiterRep;
import com.bar.barproject.Reprository.adminhyrRep;
import com.bar.barproject.Reprository.orderRep;
import com.bar.barproject.Reprository.waiterhyrRep;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.core.ipc.http.HttpSender.Response;

@Service
public class Services {
    @Autowired
    private DrinkRep drinkRep;

    @Autowired
    private adminhyrRep adminhyrRep;

    @Autowired
    private AdminRep adminRep;

    @Autowired
    private waiterhyrRep waiterhyrRep;

    @Autowired
    private WaiterRep waiterRep;
@Autowired
private orderRep orderRep;
    
    
   
    public ResponseEntity<String> post1( drinks drink){
        try{
            drinkRep.save(drink);
          return  ResponseEntity.ok("Ok");
        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
     
     
    } 
    public ResponseEntity<String> getdrink(Integer id){

   
       
        try{
         adminhyr data =   adminhyrRep.getReferenceById(id);
         admin data1 = adminRep.getReferenceById(id);
         if(data1.getUser().equals(data.getUser())){
            List<drinks> dataall =  drinkRep.findAll();
            ObjectMapper maper =  new ObjectMapper();
          String jsondata =  maper.writeValueAsString(dataall);
                 
               return ResponseEntity.ok(jsondata);
         }else{
            throw new Exception("Not done");
         }
     
        }catch (Exception e){
return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
       
    } 
    }

    public ResponseEntity<String> get1(Integer id){
        try{
            List<waiterhyr> datahyr = waiterhyrRep.findAll();
            if(datahyr.get(0).getId()==id){
                List<drinks> dr= drinkRep.findAll();
                ObjectMapper maper = new ObjectMapper();
          String  jsondata = maper.writeValueAsString(dr);
          return ResponseEntity.ok(jsondata);
            }else{
                throw new Exception("Not done");

            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
        }


    }
    public ResponseEntity<String> delete(Integer id){
        try{
            drinkRep.deleteById(id);
            return ResponseEntity.ok("Ok");

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
     
        
    }
    public ResponseEntity<String> update(drinks drink){

        try{
            Integer number = drink.getId();
            drinks data =  drinkRep.findById(number).get();
            data.setName(drink.getName());
            data.setPrice(drink.getPrice());
            data.setQuantity(drink.getQuantity());
         drinkRep.save(data);
         return ResponseEntity.ok("Ok");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }
    public ResponseEntity<String> addWaiter(waiter waiterdata){
        try{
            waiterRep.save(waiterdata);
       return  ResponseEntity.ok("Ok");

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }


    }

    public ResponseEntity<String> getwait(Integer id){

        try{
            List<adminhyr> datahyr = adminhyrRep.findAll();
            if(datahyr.get(0).getId()==id){
             List<waiter> wt = waiterRep.findAll();
             for(int i =0; i<wt.size(); i++){
                 wt.get(i).setDisplay("none");;
             }
         ObjectMapper map =  new ObjectMapper();
         String data =  map.writeValueAsString(wt);
         return ResponseEntity.ok(data);
             
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }public ResponseEntity<String> deletewaiter(Integer id){
        try{

waiterRep.deleteById(id);
return ResponseEntity.ok("Ok");
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }
    public  ResponseEntity<String> updateWaiter(waiter waiter){
        try{
            Integer id = waiter.getId();
            List<orders> data2 =  orderRep.findByIduser(waiter.getId());
            for(int i =0; i<data2.size();i++){
                data2.get(i).setName(waiter.getName());
            }
            
            waiter data = waiterRep.findById(id).get();
            data.setName(waiter.getName());
            data.setPassword(waiter.getPassword());
            waiterRep.save(data);
            
               return ResponseEntity.ok("Ok");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }public ResponseEntity<String> updateDrinks(List<drinks> drinks){
        try{
            for(int i = 0; i<drinks.size();i++){
                Integer q = drinks.get(i).getId();
                drinks ss = drinkRep.findById(q).get();
                
                ss.setQuantity(drinks.get(i).getQuantity());
                drinkRep.save(ss);
              
            }
            return ResponseEntity.ok("Ok");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
    }public ResponseEntity<String> addorder(List<orders> orderList){
        try{
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
                   return ResponseEntity.ok("Ok");
        
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
                return ResponseEntity.ok("Ok");
        }

    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }
}
public ResponseEntity<Integer> getwaiterhyr1(String name){
   
   try{
    List<waiterhyr> datahyr = waiterhyrRep.findAll();
    if(datahyr.size()!=0&&datahyr.get(0).getName().equals(name)){
        List<waiterhyr> data  = waiterhyrRep.findAll();
        Integer id = data.get(0).getId();
        List<orders> ord =  orderRep.findByIduser(id);
        Integer daynumb = ord.get(ord.size()-1).getDay();
     
       return ResponseEntity.ok(daynumb);
   
    }else{
        return ResponseEntity.ok(-1);
    }  

   }catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);

   }}
   public   Integer getwaiterhyr11(Integer id){
   try{
    List<waiterhyr> datahyr = waiterhyrRep.findAll();

if(datahyr.size()!=0&&datahyr.get(0).getId().equals(id)){
    List<waiterhyr> data  = waiterhyrRep.findAll();
    Integer id1 = data.get(0).getId();
    List<orders> ord =  orderRep.findByIduser(id1);
 
    return ord.get(ord.size()-1).getDay();
}else{
    return (-1);
}
   }catch(Exception e){
    return (-1);
   }

   }
   public ResponseEntity<String> getorders(){

    try{
        List<waiterhyr> data  = waiterhyrRep.findAll();
        Integer id = data.get(0).getId();
        List<orders> ord =  orderRep.findByIduser(id);
        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(ord);
        return ResponseEntity.ok(json);

    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
    }
   }
   public ResponseEntity<String> updatestate(){
    try{
        List<waiterhyr> waiter =  waiterhyrRep.findAll();
        List<orders> order =  orderRep.findByIduser(waiter.get(0).getId());
        for(int i =0; i<order.size();i++){
            order.get(i).setDayaktiv(1);
            orderRep.save(order.get(i));
        }
return ResponseEntity.ok("Ok");
    }catch(Exception e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }
   }public ResponseEntity<String> getwaiters(Integer id){
  try{
     List<adminhyr> datahyr =  adminhyrRep.findAll();
    if(datahyr.size()!=0&&datahyr.get(0).getId()==id){
      List<String> data = orderRep.findDistinctColumnValues();
      ObjectMapper data2 =  new ObjectMapper();
      String data3 = data2.writeValueAsString(data);
      
      return ResponseEntity.ok(data3);
    }else{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }

  }catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");


  }
   }public ResponseEntity<String> getday(waiterhyr datas){
    try{
        List<orders> datawaiter = orderRep.findByName(datas.getName());
        waiterhyrRep.deleteAll();
        waiterhyr hyr =  new waiterhyr();
        hyr.setId(datawaiter.get(0).getIduser());
        hyr.setName(datawaiter.get(0).getName());
        hyr.setPassword("");
        waiterhyrRep.save(hyr);
        
     return  ResponseEntity.ok("Ok");
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }

   }public ResponseEntity<String> loginadmin(admin data){
    try{

       
        List<admin> data1 = adminRep.findByUser(data.getUser());
            if(data.getPassword().equals(data1.get(0).getPassword())){
               
                adminhyrRep.deleteAll();
                adminhyr datahyr = new adminhyr();
                datahyr.setId(data1.get(0).getId());
                datahyr.setUser(data1.get(0).getUser());
                datahyr.setPassoword(data1.get(0).getPassword());
              adminhyrRep.save(datahyr);
                
                return ResponseEntity.ok(data1.get(0).getId().toString());
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data is not correct");

            }
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data is not correct");

    }

   }public ResponseEntity<String> loginwaiter(waiter data){


try{
    List<waiter> data1 =  waiterRep.findByName(data.getName());
if(data1.size()!=0&&data1.get(0).getPassword().equals(data.getPassword())){
  List<waiterhyr> data2 = waiterhyrRep.findAll();
  waiterhyr data3 = new waiterhyr();
waiterhyrRep.deleteAll();

  data3.setId(data1.get(0).getId());
data3.setName(data1.get(0).getName());
data3.setPassword(data1.get(0).getPassword());
  
  waiterhyrRep.save(data3);

    return ResponseEntity.ok(data1.get(0).getId().toString());
}else{
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data is not correct");
   


}
}catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data is not correct");


}






   }public ResponseEntity<String> deleteacc(){
    try{
        waiterhyrRep.deleteAll();
        adminhyrRep.deleteAll();
        return ResponseEntity.ok("Ok");

    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data is not correct");


    }
   }

}
  
