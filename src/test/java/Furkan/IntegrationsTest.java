package Furkan;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*; 
import java.util.*; 

//import static org.easymock.EasyMock; 

import org.junit.Test; 
import Furkan.meldeauskunft.domain.Resident;
import Furkan.meldeauskunft.repository.ResidentRepository;
import Furkan.meldeauskunft.repository.stub; 
import Furkan.meldeauskunft.service.BaseResidentService;
import Furkan.meldeauskunft.service.ResidentService;
import Furkan.meldeauskunft.service.ResidentServiceException; 

public class IntegrationsTest { 
     
    List<Resident> liste = new ArrayList<Resident>(); 
    List<Resident> result = new ArrayList<Resident>(); 
    List<Resident> result1 = new ArrayList<Resident>(); 
    List<Resident> result2 = new ArrayList<Resident>(); 
     
    @Test 
    public void getFilteredResidentsTest() { 
         
        Resident r1 = new Resident(); 
        r1.setGivenName("Stephan"); 
        r1.setFamilyName("Mosbach"); 
        r1.setStreet("Dammstrasse"); 
        r1.setCity("Gosheim"); 
        r1.setDateOfBirth(null); 
         
         
        Resident r2 = new Resident(); 
        r2.setGivenName("Lilli"); 
        r2.setFamilyName("Essmer"); 
        r2.setStreet("Bondelstrasse"); 
        r2.setCity("Brigachtal"); 
        r2.setDateOfBirth(null); 
     
        liste.add(r1); 
        liste.add(r2); 
         
        stub stub = new stub(liste); 
         
        BaseResidentService service = new BaseResidentService(); 
        service.setResidentRepository(stub); 
         
        Resident filterResident = new Resident("L*","*","*","*", null); 
        Resident filterResident2 = new Resident("*","*","B*","*", null); 
        Resident filterResident3 = new Resident("*", "E*", "*", "*", null); 
                 
        result = service.getFilteredResidentsList(filterResident); 
        result1 = service.getFilteredResidentsList(filterResident2); 
        result2 = service.getFilteredResidentsList(filterResident3); 
         
         
        assertEquals(r2.getGivenName(), result.get(0).getGivenName()); 
        assertEquals(r2.getStreet(), result1.get(0).getStreet()); 
        assertEquals(r2.getFamilyName(), result2.get(0).getFamilyName()); 
    } 
    @Test 
    public void getUniqueResidentsTest() throws ResidentServiceException { 
         
        List<Resident> liste = new ArrayList<Resident>(); 
         
        Resident r1 = new Resident(); 
        r1.setGivenName("Stephan"); 
        r1.setFamilyName("Mosbach"); 
        r1.setStreet("Dammstrasse"); 
        r1.setCity("Gosheim"); 
        r1.setDateOfBirth(null); 
         
         
        Resident r2 = new Resident(); 
        r2.setGivenName("Lilli"); 
        r2.setFamilyName("Essmer"); 
        r2.setStreet("Bondelstrasse"); 
        r2.setCity("Brigachtal"); 
        r2.setDateOfBirth(null); 
         

        liste.add(r1); 
        liste.add(r2); 

         
        stub stub = new stub(liste); 
         
        BaseResidentService service = new BaseResidentService(); 
        service.setResidentRepository(stub); 
         
        Resident UniqueResident = new Resident("Stephan","Mosbach","Dammstrasse","Gosheim", null); 
        Resident UniqueResident2 = new Resident("Lilli","Essmer","Bondelstrasse","Brigachtal", null); 
        Resident UniqueResident3 = new Resident("Lilli","Essmer","Bondelstrasse","Brigachtal", null); 
                 
        Resident result = service.getUniqueResident(UniqueResident); 
        Resident result1 = service.getUniqueResident(UniqueResident2); 
        Resident result2 = service.getUniqueResident(UniqueResident3); 
         
         
        assertEquals(r1.getGivenName(), result.getGivenName()); 
        assertEquals(r2.getFamilyName(), result1.getFamilyName()); 
        assertEquals(r2.getStreet(), result2.getStreet()); 
    } 
    
        @Test 
        public void testGetFilteredResidentsList3() 
        { 
        List<Resident> list = new LinkedList<Resident>(); 
        list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date())); 
        list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date())); 
        list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date())); 
        ResidentRepository stub = new stub(list); 

        ResidentService rs = new BaseResidentService(); 
        ((BaseResidentService) rs).setResidentRepository(stub); 
        List<Resident> result = rs.getFilteredResidentsList(new Resident("Ma*", "Mu*", "*straﬂe", "Muster*", new Date())); 

        assertEquals(2, result.size()); 

        assertEquals("Max", result.get(0).getGivenName()); 
        assertEquals("Mustermann", result.get(0).getFamilyName()); 
        assertEquals("Musterstraﬂe", result.get(0).getStreet()); 
        assertEquals("Musterstadt", result.get(0).getCity()); 
        assertEquals("Martina", result.get(1).getGivenName()); 
        assertEquals("Mustermann", result.get(1).getFamilyName()); 
        assertEquals("Albertstraﬂe", result.get(1).getStreet()); 
        assertEquals("Musterdorf", result.get(1).getCity()); 
        } 
        @Test 
        public void testGetUniqueResident1() 
        { 
        List<Resident> list = new LinkedList<Resident>(); 
        ResidentRepository stub = new stub(list); 

        ResidentService rs = new BaseResidentService(); 
        ((BaseResidentService) rs).setResidentRepository(stub); 

        try { 
        Resident result = rs.getUniqueResident(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", null)); 
        fail("getUniqueResident on empty List should throw exception"); 
        } catch (ResidentServiceException e) { 
        assertEquals("Suchanfrage lieferte kein eindeutiges Ergebnis!", e.getMessage()); 
        } 
        } 
        @Test 
        public void testGetUniqueResident2() 
        { 
        List<Resident> list = new LinkedList<Resident>(); 
        ResidentRepository stub = new stub(list); 

        ResidentService rs = new BaseResidentService(); 
        ((BaseResidentService) rs).setResidentRepository(stub); 

        try { 
        Resident result = rs.getUniqueResident(new Resident("*", "*", "*", "*", null)); 
        fail("getUniqueResident with should throw exception"); 
        } catch (ResidentServiceException e) { 
        assertEquals("Wildcards (*) sind nicht erlaubt!", e.getMessage()); 
        } 
        } 


        @Test 
        public void testGetUniqueResident3() 
        { 
        List<Resident> list = new LinkedList<Resident>(); 
        list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date())); 
        list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date())); 
        list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date())); 
        ResidentRepository stub = new stub(list); 

        ResidentService rs = new BaseResidentService(); 
        ((BaseResidentService) rs).setResidentRepository(stub); 

        try { 
        Resident result = rs.getUniqueResident(new Resident("Max", "Mustermann", null, null, null)); 
        assertEquals("Max", result.getGivenName()); 
        assertEquals("Mustermann", result.getFamilyName()); 
        assertEquals("Musterstraﬂe", result.getStreet()); 
        assertEquals("Musterstadt", result.getCity()); 
        } catch (ResidentServiceException e) { 
        fail(e.getMessage()); 
        e.printStackTrace(); 
        } 
        } 

        @Test 
        public void testGetUniqueResidentMock() 
        { 
        List<Resident> list = new LinkedList<Resident>(); 
        list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date())); 
        list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date())); 
        list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date())); 

        ResidentRepository mock = createMock(ResidentRepository.class); 
        expect(mock.getResidents()).andReturn(list); 
        replay(mock); 

        ResidentService rs = new BaseResidentService(); 
        ((BaseResidentService) rs).setResidentRepository(mock); 

        try { 
        Resident result = rs.getUniqueResident(new Resident("Max", "Mustermann", null, null, null)); 
        assertEquals("Max", result.getGivenName()); 
        assertEquals("Mustermann", result.getFamilyName()); 
        assertEquals("Musterstraﬂe", result.getStreet()); 
        assertEquals("Musterstadt", result.getCity()); 
        } catch (ResidentServiceException e) { 
        fail(e.getMessage()); 
        e.printStackTrace(); 
        } 

        verify(mock); 
        } 
     
     
} 
