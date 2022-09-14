package contactkeeper.demo.repository;


import contactkeeper.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query("select c from Contact c where c.userId=?1")
    List<Contact> findAllByMyUser(String userId);
}
