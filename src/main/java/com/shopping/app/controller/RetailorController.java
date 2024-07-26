package com.shopping.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.app.exception.CreateException;
import com.shopping.app.exception.SearchException;
import com.shopping.app.model.Retailor;
import com.shopping.app.repository.RetailorRepository;
import com.shopping.app.utils.request.RetailorLoginRequest;

@Component
public class RetailorController {
    
    @Autowired
    private RetailorRepository retailorRepository;

    public RetailorController() {

    }

    public String createRetailor(Retailor retailor) throws CreateException {
        try {
            return retailorRepository.save(retailor).getId();
        } catch(Exception e) {
            throw new CreateException(e);
        }
        
    }

    public String verifyRetailor(RetailorLoginRequest retailorLoginRequest) throws SearchException {
        try {
            String name = retailorLoginRequest.name;
            String password = retailorLoginRequest.password;

            if(retailorRepository.findRetailorNameAndPass(name, password) != null){
                return retailorRepository.findRetailorNameAndPass(name, password).getId();
            }
            else{
                return "";
            }
            
        } catch(Exception e) {
            throw new SearchException(e);
        }
    }

    public Retailor getRetailor(String id) throws SearchException {
        try {
            return retailorRepository.findById(id).get();
        } catch(Exception e) { 
            throw new SearchException(e);
        }
    }
}
