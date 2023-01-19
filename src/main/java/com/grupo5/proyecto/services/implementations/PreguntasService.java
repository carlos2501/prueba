package com.grupo5.proyecto.services.implementations;


import com.grupo5.proyecto.entities.Question;
import com.grupo5.proyecto.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PreguntasService {

    @Autowired
    Question question;

    @Autowired
    QuestionRepo qRepo;

    public Question generaPregunta() {
        List<Question> allQues = qRepo.findAll();
        Random random = new Random();
            int rand = random.nextInt(allQues.size());
            return allQues.get(rand);
    }

}
