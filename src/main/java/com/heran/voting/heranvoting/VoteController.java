package com.heran.voting.heranvoting;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    private final static Logger LOGGER=Logger.getLogger(CandidateController.class);
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping(value="/total",produces = {"application/json"})
    public ResponseEntity total(){
        List<Resul>
    }

}
