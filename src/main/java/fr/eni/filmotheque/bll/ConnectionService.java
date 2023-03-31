package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ConnectionService {
    Optional<Member> login(String email, String password);
}
