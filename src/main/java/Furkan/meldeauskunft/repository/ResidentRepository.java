package Furkan.meldeauskunft.repository;

import java.util.List;

import Furkan.meldeauskunft.domain.Resident;
/**
 * @author Stefan Betermieux
 */
public interface ResidentRepository {
	

  List<Resident> getResidents();


}