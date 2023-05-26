package com.bar.barproject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.bar.barproject.Model.Verify;
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
import com.bar.config.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


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

@Autowired
private TokenUtil tokenUtil;
    
    
   
    public ResponseEntity<String> post1( drinks drink){
        try{
            String role = tokenUtil.getRoleFromToken(drink.getToken());
            if(role.equals("admin")){
                drinkRep.save(drink);
                return  ResponseEntity.ok("Ok");
            }else{
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

            }
        
        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
     
     
    } 
    public ResponseEntity<String> getdrink(Verify ver){

   
       
        try{
            String role =  tokenUtil.getRoleFromToken(ver.getToken());
            if(role.equals("admin")&&(ver.getId().equals("1"))){
                List<drinks> dataall =  drinkRep.findAll();
                ObjectMapper maper =  new ObjectMapper();
              String jsondata =  maper.writeValueAsString(dataall);
                     
                   return ResponseEntity.ok(jsondata);   
            }
     else{
            throw new Exception("Not done");
         }
     
        }catch (Exception e){
return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
       
    } 
    }

    public ResponseEntity<String> get1(Verify body){
        try{
            String role = tokenUtil.getRoleFromToken(body.getToken());
            List<waiterhyr> datahyr = waiterhyrRep.findAll();
            if(role.equals("waiter")&&(tokenUtil.getIdFromToken(body.getToken()).equals(body.getId()))){
                List<drinks> dr= drinkRep.findAll();
                ObjectMapper maper = new ObjectMapper();
          String  jsondata = maper.writeValueAsString(dr);
          return ResponseEntity.ok(jsondata);
            }else{
                throw new Exception("Not done");

            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tokenUtil.getIdFromToken(body.getToken())+"_____"+body.getId());
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
            String role = tokenUtil.getRoleFromToken(drink.getToken());
            if(role.equals("admin")){
                Integer number = drink.getId();
                drinks data =  drinkRep.findById(number).get();
                data.setName(drink.getName());
                data.setPrice(drink.getPrice());
                data.setQuantity(drink.getQuantity());
             drinkRep.save(data);
             return ResponseEntity.ok("Ok");
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
  
            }
        
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }
    public ResponseEntity<String> addWaiter(waiter waiterdata){
        try{
            String role = tokenUtil.getRoleFromToken(waiterdata.getToken());
            if(role.equals("admin")){
                waiterRep.save(waiterdata);
                return  ResponseEntity.ok("Ok");
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

            }
          

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }


    }

    public ResponseEntity<String> getwait(Verify vebody){

        try{
            String role =  tokenUtil.getRoleFromToken(vebody.getToken());
            
          
            if(role.equals("admin")&&(vebody.getId().equals("1"))){
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

    }public ResponseEntity<String> deletewaiter(Verify ver){
        try{
            String role = tokenUtil.getRoleFromToken(ver.getToken());

            if(role.equals("admin")){

                waiterRep.deleteById(Integer.parseInt(ver.getId()));
                return ResponseEntity.ok("Ok");

            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

            }

        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }
    public  ResponseEntity<String> updateWaiter(waiter waiter){
        try{
            String role = tokenUtil.getRoleFromToken(waiter.getToken());
            if(role.equals("admin")){
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
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

            }
           
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }public ResponseEntity<String> updateDrinks(List<drinks> drinks){
        try{
            String role =  tokenUtil.getRoleFromToken(drinks.get(0).getToken());
            if(role.equals("waiter")){
                for(int i = 0; i<drinks.size();i++){
                    Integer q = drinks.get(i).getId();
                    drinks ss = drinkRep.findById(q).get();
                    
                    ss.setQuantity(drinks.get(i).getQuantity());
                    drinkRep.save(ss);
                  
                }
                return ResponseEntity.ok("Ok");
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

            }
           
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
    }public ResponseEntity<String> addorder(List<orders> orderList){
      
        try{
          
            String role = tokenUtil.getRoleFromToken(orderList.get(0).getToken());
            if(role.equals("waiter")){
                
          
          Integer datawaiterhyr =  Integer.parseInt(tokenUtil.getIdFromToken(orderList.get(0).getToken()));
            List<orders> dataorder = orderRep.findByIduser(datawaiterhyr);
        
           
            if(dataorder.size()==0){
              
                for(int j =0; j<=orderList.size(); j++){
                    orderList.get(j).setIduser(datawaiterhyr);
                    orderList.get(j).setName(tokenUtil.getNameFromToken(orderList.get(0).getToken()));
                    orderList.get(j).setPrice(orderList.get(j).getPrice()*orderList.get(j).getQuatity());
                  // orderList.get(j).setPrice(200);
                   orderList.get(j).setDay(1);
                    orderList.get(j).setDayaktiv(0);
                  
                          orderRep.save(orderList.get(j));
        
                }
               
                   return ResponseEntity.ok("Ok");
                  
            }else  {
             
                for(int j =0; j<=orderList.size()-1; j++){
                    orderList.get(j).setIduser(datawaiterhyr);

                    orderList.get(j).setName(tokenUtil.getNameFromToken(orderList.get(0).getToken()));
              

                 orderList.get(j).setPrice(orderList.get(j).getPrice()*orderList.get(j).getQuatity());
                 // orderList.get(j).setPrice(300);
              
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
        }}else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

    }catch(Exception e){
       
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }
}
public ResponseEntity<Integer> getwaiterhyr1(Verify ver){
   
   try{
    String role = tokenUtil.getRoleFromToken(ver.getToken());
    if(role.equals("admin")&&(ver.getId().equals("1"))){
        List<waiterhyr> datahyr = waiterhyrRep.findAll();
     
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
   public   Integer getwaiterhyr11(Verify bod){
   try{
    String role = tokenUtil.getRoleFromToken(bod.getToken());
    

if(role.equals("waiter")&&(tokenUtil.getIdFromToken(bod.getToken()).equals(bod.getId()))){
    
    List<orders> ord =  orderRep.findByIduser(Integer.parseInt(bod.getId()));
 
    return ord.get(ord.size()-1).getDay();
}else{
    return (-1);
}
   }catch(Exception e){
    return (-1);
   }

   }
   public ResponseEntity<String> getorders(Verify bod){

    try{
        String role =  tokenUtil.getRoleFromToken(bod.getToken());
        if(role.equals("waiter")&&(bod.getId().equals(tokenUtil.getIdFromToken(bod.getToken())))){
            List<orders> ord =  orderRep.findByIduser(Integer.parseInt(bod.getId()));
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(ord);
            return ResponseEntity.ok(json);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
  
        }

     

    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
    }
   }
   public ResponseEntity<String> updatestate(Verify bod){
    try{
        String role =  tokenUtil.getRoleFromToken(bod.getToken());

        if(role.equals("waiter")&&(bod.getId().equals(tokenUtil.getIdFromToken(bod.getToken())))){
            List<orders> order =  orderRep.findByIduser(Integer.parseInt(bod.getId()));
            for(int i =0; i<order.size();i++){
                order.get(i).setDayaktiv(1);
                orderRep.save(order.get(i));
            }
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
       
return ResponseEntity.ok("Ok");
    }catch(Exception e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }
   }public ResponseEntity<String> getwaiters(Verify ver){
  try{
    String role = tokenUtil.getRoleFromToken(ver.getToken());
    if(role.equals("admin")&&(ver.getId().equals("1"))){
        List<adminhyr> datahyr =  adminhyrRep.findAll();
        
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
        String role = tokenUtil.getRoleFromToken(datas.getToken());
        if(role.equals("admin")){
            List<orders> datawaiter = orderRep.findByName(datas.getName());
            waiterhyrRep.deleteAll();
            waiterhyr hyr =  new waiterhyr();
            hyr.setId(datawaiter.get(0).getIduser());
            hyr.setName(datawaiter.get(0).getName());
            hyr.setPassword("");
            waiterhyrRep.save(hyr);
            
         return  ResponseEntity.ok("Ok");
        }
      else{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

      }
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }

   }public ResponseEntity<Verify> loginadmin(admin data){
    try{

       
        List<admin> data1 = adminRep.findByUser(data.getUser());
            if(data.getPassword().equals(data1.get(0).getPassword())){
               
                adminhyrRep.deleteAll();
                adminhyr datahyr = new adminhyr();
                datahyr.setId(data1.get(0).getId());
                datahyr.setUser(data1.get(0).getUser());
                datahyr.setPassoword(data1.get(0).getPassword());
              adminhyrRep.save(datahyr);
                String token  =  tokenUtil.generateTokenadmin(data);
                Verify respo = new Verify(data1.get(0).getId().toString(), token);
                return ResponseEntity.ok(respo);
            }else{
                Verify respo = new Verify("Data is not correct", "Errortoken");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respo);

            }
    }catch(Exception e){
        Verify respo = new Verify("Data is not correct", "Errortoken");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respo);
    }

   }public ResponseEntity<Verify> loginwaiter(waiter data){


try{
    List<waiter> data1 =  waiterRep.findByName(data.getName());
if(data1.size()!=0&&data1.get(0).getPassword().equals(data.getPassword())){
 
String token1 = tokenUtil.generateTokenwaiter(data1.get(0));
  Verify verbody = new Verify(data1.get(0).getId().toString(), token1);
 
  

    return ResponseEntity.ok(verbody);
}else{

   Verify respo = new Verify("Data is not correct", "Errortoken");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respo);
   


}
}catch(Exception e){
    Verify respo = new Verify("Data is not correct", "Errortoken");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respo);

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
  
