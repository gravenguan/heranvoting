package com.heran.voting.heranvoting;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.*;

@RestController
public class CandidateController {
    private final static Logger LOGGER=Logger.getLogger(CandidateController.class);
    @Autowired
    private CandidateRepository candidateRepository;

//    @GetMapping(value="/candidates",produces = {"application/json"})
//    public ResponseEntity retrieveAllCandidates(){
//        return new ResponseEntity(candidateRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value="/candidates/{id}",produces = {"application/json"})
    public ResponseEntity retrieveOneCandidate(@PathVariable Long id){
        Optional<Candidate> candidate=candidateRepository.findById(id);
        if(!candidate.isPresent()){
            return new ResponseEntity(Collections.singletonMap("message","Candidate with id "+id+" does not exist"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(candidate.get(),HttpStatus.OK);
    }

    @GetMapping(value="/candidates",produces = {"application/json"})
    public ResponseEntity retrieveCandidateByFirstname(@RequestParam(value="firstName", required = false) String firstName){
        List<Candidate> candidate=null;
        if(firstName != null){
            candidate=candidateRepository.findByFirstName(firstName);
        }else{
            candidate=candidateRepository.findAll();
        }

        return new ResponseEntity(candidate,HttpStatus.OK);
    }


    @PostMapping(value="/candidates",produces = {"application/json"})
    public ResponseEntity createCandidate(@Valid @RequestBody Candidate candidate){
        Candidate saveCandidate=candidateRepository.save(candidate);
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveCandidate.getId()).toUri());
        return new ResponseEntity(candidate,resHeaders,HttpStatus.CREATED);
    }

}
