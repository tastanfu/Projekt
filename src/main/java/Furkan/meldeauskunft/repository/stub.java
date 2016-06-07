package Furkan.meldeauskunft.repository; 

import java.util.List; 

import Furkan.meldeauskunft.domain.Resident; 

public class stub implements ResidentRepository{ 
     
    private List<Resident> residents; 
     
    public stub(List<Resident> liste){ 
        residents = liste; 
    } 
    public List<Resident> getResidents() { 
        // TODO Auto-generated method stub 
        return residents; 
    } 
} 
