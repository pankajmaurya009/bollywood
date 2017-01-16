package com.allstate.services;

import com.allstate.entities.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class MovieServiceTest {
    @Autowired
    private MovieService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateMovie() throws Exception{
        Movie before = new Movie();
        before.setTitle("Reshma ki jawani");
        Movie after = this.service.create(before);
        assertEquals(2, after.getId());
        assertEquals("Reshma ki jawani", after.getTitle());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldNotCreateMovie() throws Exception{
        Movie before = new Movie();
        Movie after = this.service.create(before);
    }

    @Test
    public void shouldReadOneMovie() throws Exception{
        Movie after = this.service.readOne(1);
        assertTrue(after.getTitle().equals("The Avengers"));
    }

    @Test
    public void shouldNotReadOneMovie() throws Exception{
        Movie after = this.service.readOne(5);
        assertNull(after);
    }

    @Test

    public void shouldReadAllMovies() throws Exception{
        ArrayList<Movie> moviesList = (ArrayList<Movie>) this.service.readAll();
        assertEquals(1, moviesList.size());
        assertEquals("The Avengers",moviesList.get(0).getTitle());
    }


    @Test
    public void shouldReadMovieByTitle() throws Exception{
        Movie m = this.service.findByTitle("The Avengers");
        assertEquals("PG", m.getRating());
    }

    @Test
    public void shouldNotReadMovieByBadTitle() throws Exception{
        Movie m = this.service.findByTitle("The Hero");
        assertNull(m);
    }

    @Test
    public void shouldDeleteMovieById() throws Exception{
        this.service.deleteById(1);
        Movie after = this.service.readOne(1);
        assertNull(after);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldNotDeleteMovieByBadId() throws Exception{
        this.service.deleteById(3);
    }

    @Test
    public void shouldUpdateMovieById() throws Exception{
        Movie before = new Movie();
        before.setTitle("Reshma ki jawani");
        Movie m = this.service.update(1,before);
        assertEquals(1, m.getId());
        assertEquals("Reshma ki jawani",m.getTitle());
        assertEquals(1, m.getVersion());

    }

    @Test(expected = NullPointerException.class)
    public void shouldNotUpdateMovieByBadId() throws Exception{
        Movie before = new Movie();
        before.setTitle("Reshma ki jawani");
        Movie m = this.service.update(2,before);
        }


}