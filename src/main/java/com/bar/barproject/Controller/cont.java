package com.bar.barproject.Controller;


//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.bar.barproject.Model.Verify;
import com.bar.barproject.Model.admin;
import com.bar.barproject.Model.drinks;
import com.bar.barproject.Model.orders;
import com.bar.barproject.Model.waiter;
import com.bar.barproject.Model.waiterhyr;
import com.bar.barproject.Reprository.WaiterRep;
import com.bar.barproject.Reprository.adminhyrRep;
import com.bar.barproject.Reprository.waiterhyrRep;
import com.bar.barproject.Service.Services;
import com.bar.barproject.Reprository.AdminRep;
import com.bar.barproject.Reprository.DrinkRep;
import com.bar.barproject.Reprository.orderRep;


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
@Autowired
private Services services;

    @PostMapping(value="/addDrinks")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity<String> post(@RequestBody final drinks drink){
       
        try{
           ResponseEntity<String> data =  services.post1(drink);
            return data;
        }catch (Exception e){
return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
       }
    } 
     @PostMapping(value="/getDrinks/{id}")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity<String> get(@PathVariable Integer id, @RequestBody Verify ver){
        
       try{
        ResponseEntity<String> data = services.getdrink(ver);
        return(data);

       }catch(Exception e){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

       }
       
    }
  @PostMapping(value="/getDrinks1/{id}")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity<String> get1(@PathVariable Integer id, @RequestBody Verify body){
      try{
        ResponseEntity<String> data = services.get1(body);
return(data);
      }catch(Exception e){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
      }
    }
    @DeleteMapping(value="/deletedrink")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity<String> delete(@RequestParam Integer id){
try{
    ResponseEntity<String>  response = services.delete(id);
    return(response);

}catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");


}
    
}

  @PutMapping(value="/updateDrink")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> update(@RequestBody  drinks drink){
 try{
ResponseEntity<String> response = services.update(drink);
return response;
 }catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

 }


}

@PostMapping(value="/addWaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> addwaiter(@RequestBody final waiter waiter){
  

    try{
        ResponseEntity<String> data = services.addWaiter(waiter);
        return data;
    }catch(Exception e){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }
}

@PostMapping(value="/getWaiter/{id}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> getwait(@PathVariable Integer id, @RequestBody Verify vebody){
 try{
    ResponseEntity<String> data =  services.getwait(vebody);
    return data;

 }catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

 }
}

@DeleteMapping(value="/deletewaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> deletewaiter(@RequestParam Integer id,@RequestBody Verify verify){

try{
    ResponseEntity<String> data =  services.deletewaiter(verify);
    return data;

}catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

}

}

@PutMapping("/updatawaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> updatewaiter(@RequestBody waiter waiter){
try{
    ResponseEntity<String> data =  services.updateWaiter(waiter) ;
return data;
}catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

}
}

@PutMapping(value="/updateDrinksorder")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> updateDrinks(@RequestBody List<drinks> drinks){
   
try{
    ResponseEntity<String> data =  services.updateDrinks(drinks);
    return data;

}catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

}
}

@PostMapping(value="/addorder")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> addorder(@RequestBody List<orders> orderList){
    try{
        ResponseEntity<String> data = services.addorder(orderList);
        return data;
    }catch(Exception e){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

    }
}

@PostMapping(value="/getdays/{name}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<Integer> getwaiterhyr1(@PathVariable String name,@RequestBody Verify ver){
try{
    ResponseEntity<Integer> num = services.getwaiterhyr1(ver);
    return num;

}catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);

}
  
}


@PostMapping(value="/getdays1/{id}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public Integer getwaiterhyr11(@PathVariable Integer id, @RequestBody Verify bod){
try{
    Integer id1 = services.getwaiterhyr11(bod);
    return id1;

}catch(Exception e){
    return (-1);

}
  
}

@PostMapping(value="/allorders")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public ResponseEntity<String> getorders(@RequestBody Verify bod){
    try{
        ResponseEntity<String> data =  services.getorders(bod);
        return data;

    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not dome");

    }
 


}

@PutMapping(value="/updatestateday")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> updatestate(Verify bod){
   try{
ResponseEntity<String> data = services.updatestate(bod);
return data;

   }catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not dome");

   }
}

@PostMapping(value="/getwaitershistory/{id}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> getwaiters(@PathVariable Integer id, @RequestBody Verify ver){
try{
    ResponseEntity<String> data1 =  services.getwaiters(ver);
    return  data1;

}catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not dome");

}
  
   
}

@PostMapping(value="/openwaiterhistory")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public  ResponseEntity<String>  getday(@RequestBody waiterhyr datas){

  try{
    ResponseEntity<String> data = services.getday(datas);
    return data;

  }catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not dome");

  }

}

@PostMapping(value="/loginadmin")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<Verify> loginadmin(@RequestBody admin data){
   try{
    ResponseEntity<Verify> data1 = services.loginadmin(data);
    return data1;


   }catch(Exception e){
    Verify respo = new Verify("Data is not correct", "Errortoken");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respo);

   }

  
}

@PostMapping(value="/loginwaiter")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<Verify> loginwaiter(@RequestBody waiter data){
try{
ResponseEntity<Verify> data1 =  services.loginwaiter(data);
return data1;
}catch(Exception e){
    Verify respo = new Verify("Data is not correct", "Errortoken");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respo);


}



}

@DeleteMapping(value="/deleteaccount")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String>  deleteacc(){
   try{
    ResponseEntity<String> data =  services.deleteacc();
    return data;

   }catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

   }
}




}

