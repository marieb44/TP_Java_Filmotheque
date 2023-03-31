package fr.eni.filmotheque.bll.mock;

import fr.eni.filmotheque.bll.ConnectionService;
import fr.eni.filmotheque.bo.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionServiceMock implements ConnectionService {

    private List<Member> lstMembers;
    //private static List<Member> lstMembers; // correction static inutile car @Service => singleton

    public ConnectionServiceMock() {
        lstMembers = new ArrayList<>();

        lstMembers.add(new Member(1, "Grit","Julien", "admNeil", "bla-bla", false));
        lstMembers.add(new Member(2, "Berger", "Marie","marieb44", "654321", false));
    }
    @Override
    public Optional<Member> login(String pseudo, String mdp) {

        Optional<Member> optMembre = lstMembers
                .stream()
                .filter(member -> member.getLogin().equals(pseudo) && member.getPassword().equals(mdp))
                .findFirst();
        return optMembre;

//        Optional<Member> optMember = Optional.empty();
//        for (Member m: lstMembers) {
//            System.out.println("Connect " + m + "//pseudo : " + pseudo + " mdp : " + mdp);
//            if (m.getLogin() == pseudo && m.getPassword() == mdp) {
//                optMember = Optional.of(m);
//                break;
//            }
//        }
//        return optMember;
    }
}
