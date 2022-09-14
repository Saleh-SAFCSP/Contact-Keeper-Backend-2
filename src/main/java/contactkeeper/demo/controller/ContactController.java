package contactkeeper.demo.controller;

import contactkeeper.demo.DTO.Api;
import contactkeeper.demo.model.Contact;
import contactkeeper.demo.model.MyUser;
import contactkeeper.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getContactsByUserId(user.getId()));
    }

    @PostMapping
    public ResponseEntity<Api> addContact(@AuthenticationPrincipal MyUser user,@RequestBody @Valid Contact contact){
        contactService.addContact(user,contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("New contact added",201));
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Api> updateContact(@AuthenticationPrincipal MyUser user,@PathVariable String contactId,@RequestBody @Valid Contact contact){
        contactService.updateContact(user.getId(),contactId,contact);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Contact updated",200));
    }

    @DeleteMapping
    public ResponseEntity<Api> deleteContact(@AuthenticationPrincipal MyUser user,@PathVariable String contactId){
        contactService.deleteContact(user.getId(),contactId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Contact deleted",200));
    }
}
