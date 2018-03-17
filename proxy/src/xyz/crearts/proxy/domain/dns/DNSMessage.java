package xyz.crearts.proxy.domain.dns;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class DNSMessage {
    @Getter @Setter
    private int identification;
    @Getter @Setter
    private int control;

    @Getter
    List<DNSQuestion> questions = new ArrayList<>();
    @Getter
    List<DNSResourceRecord> answers = new ArrayList<>();
    @Getter
    List<DNSResourceRecord> authorities = new ArrayList<>();
    @Getter
    List<DNSResourceRecord> additional = new ArrayList<>();

    public void addQuestion(DNSQuestion question)  {
        questions.add(question);
    }

    public void addAnswer(DNSResourceRecord answer)  {
        answers.add(answer);
    }

    public void addAuthority(DNSResourceRecord authority)  {
        answers.add(authority);
    }

    public void addAdditional(DNSResourceRecord additional)  {
        answers.add(additional);
    }
}
