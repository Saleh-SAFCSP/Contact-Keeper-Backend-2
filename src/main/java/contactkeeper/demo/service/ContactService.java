package contactkeeper.demo.service;


import contactkeeper.demo.exceptions.ApiException;
import contactkeeper.demo.model.Contact;
import contactkeeper.demo.model.MyUser;
import contactkeeper.demo.repository.ContactRepository;
import contactkeeper.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public void addContact(MyUser user,Contact contact){
        contact.setUserId(user.getId());
        contactRepository.save(contact);
    }

    public void updateContact(String userId, String contactId, Contact contact){
        Contact updateContact=contactRepository.findById(contactId).orElseThrow(()->new ApiException("There is not contact with this id"));
        if(!updateContact.getUserId().equals(userId)){
            throw new ApiException("You don't own this contact to update it !");
        }
        updateContact.setName(contact.getName());
        updateContact.setPhoneNumber(contact.getPhoneNumber());
        contactRepository.save(updateContact);
    }

    public void deleteContact(String userId,String contactId){
        Contact deletedContact=contactRepository.findById(contactId).orElseThrow(()->new ApiException("There is not contact with this id"));;
        if(!deletedContact.getUserId().equals(userId)){
            throw new ApiException("You don't own this contact to delete it !");
        }
        contactRepository.delete(deletedContact);
    }


    public  List<Contact> getContactsByUserId(String userId) {
        List<Contact> contacts= contactRepository.findAllByMyUser(userId);
        return contacts;
    }
}
