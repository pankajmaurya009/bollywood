package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {

    private IMovieRepository repository;

    @Autowired
    public void setRepository(IMovieRepository repository) {
        this.repository = repository;
    }

    public Movie create(Movie m) {
        return this.repository.save(m);
    }

    public Movie readOne(int id) {
        return this.repository.findOne(id);
    }

    public Iterable<Movie> readAll() {
        return this.repository.findAll();
    }

    public Movie findByTitle(String title){
        return this.repository.findByTitle(title);
    }

    public void deleteById(int id){
        this.repository.delete(id);

    }
    public Movie update(int id,Movie m){
        Movie mOld = this.repository.findOne(id);
        mOld.setTitle(m.getTitle());
        mOld.setWatched(m.isWatched());
        mOld.setLength(m.getLength());
        mOld.setReleased(m.getReleased());
        mOld.setRating(m.getRating());
        return this.repository.save(mOld);

    }
}
