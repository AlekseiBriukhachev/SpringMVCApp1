package com.aleksei.hibernate;


import com.aleksei.hibernate.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

// ****** Many-To-Many ******* Delete movie (delete relation) ********************************

            Actor actor = session.get(Actor.class, 2);
            System.out.println(actor.getMovies());

            Movie movieToRemove = actor.getMovies().get(0);

            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor);

// ****** Many-To-Many ******* Create new movie and add existing actor ********************************

//            Movie movie = new Movie("Reservoir Dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
//            actor.getMovies().add(movie);
//            session.save(movie);

// ****** Many-To-Many ******* Get actor and get all movies ********************************

//            Actor actor = session.get(Actor.class, 1);
//            System.out.println(actor);
//            System.out.println(actor.getMovies());

// ****** Many-To-Many ******* Get movie and get all actors ********************************

//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());

//****** Many-To-Many ******* Create new movie and create two new actors ********************************

//            Movie movie = new Movie("Pulp Fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);

//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

            //С помощью Hibernate получите любого директора, а затем получите его
            //школу

//            Principal principal = session.get(Principal.class, 2);
//            System.out.println(principal.getSchool());

            //Получите любую школу, а затем получите ее директора.

//            School school = session.get(School.class, 3);
//            System.out.println(school.getPrincipal());

            //Создайте нового директора и новую школу и свяжите эти сущности.

//            Principal principal = new Principal("Test principal", 54);
//            School school = new School(123);
//            principal.setSchool(school);
//            session.save(principal);

            //Смените директора у существующей школы.

//            School school = session.get(School.class, 2);
//            Principal principal = session.get(Principal.class, 3);
//            school.setPrincipal(principal);

//****** One-To-One ******* Delete person *************************************** ********************************

//            Person person = session.get(Person.class, 1);
//            session.remove(person);

//****** One-To-One ******* Get person and change passport number of this person ********************************

//            Person person = session.get(Person.class, 1);
//            person.getPassport().setPassportNumber(7777777);

//****** One-To-One ******* Get passport and it owner person ********************************************************

//            Passport passport = session.get(Passport.class, 1);
//            System.out.println(passport.getPerson());

//****** One-To-One ******* Get person and his passport ***************************************************************

//            Person person = session.get(Person.class, 1);
//            System.out.println(person.getPassport().getPassportNumber());

//****** One-To-One ******* Create new person and new passport number of this person ********************************

//            Person person = new Person("Test person", 54);
//            Passport passport = new Passport(123456);
//            person.setPassport(passport);
//            session.save(person);

//****** One-To_many *************** Create new person and add items *************************************************

//            Person person = new Person("Cascade person", 32);
//            person.addItem(new Item("Item 1"));
//            person.addItem(new Item("Item 2"));
//            person.addItem(new Item("Item 3"));
//            session.save(person);


//С помощью Hibernate получите любого режиссера, а затем получите список
//его фильмов.

//            Director director = session.get(Director.class, 1);
//            System.out.println("*".repeat(100));
//            System.out.println(director);
//            System.out.println(director.getMovies());
//            System.out.println("*".repeat(100));

//Получите любой фильм, а затем получите его режиссера.

//            Movie movie = session.get(Movie.class, 9);
//            System.out.println(movie);
//            System.out.println(movie.getDirector());
//            System.out.println("*".repeat(100));

//Добавьте еще один фильм для любого режиссера.

//            Movie newMovie = new Movie("Unknown movie", 2023, director);
//            director.getMovies().add(newMovie);
//            session.save(newMovie);
//            System.out.println(director);
//            System.out.println(newMovie);
//            System.out.println("*".repeat(100));

//Создайте нового режиссера и новый фильм и свяжите эти сущности.

//            Director youngDirector = new Director("Young Director", 18);
//            Movie newUnknownMovie = new Movie("New unknown movie", 2023, youngDirector);
//            youngDirector.setMovies(new ArrayList<>(Collections.singletonList(newUnknownMovie)));
//            session.save(youngDirector);
//            session.save(newUnknownMovie);

//Смените режиссера у существующего фильма.

//            Director newDirector = session.get(Director.class, 7);
//            Movie newMovie = session.get(Movie.class, 12);
//            newMovie.getDirector().getMovies().remove(newMovie);
//            newMovie.setDirector(newDirector);
//            newDirector.getMovies().add(newMovie);
//            System.out.println("*".repeat(100));

//Удалите фильм у любого режиссера.

//            List<Movie> movies = director.getMovies();
//            for (Movie movie1 : movies){
//                if (movie1.getId() == 13){
//                    session.remove(movie1);
//                }
//            }


            session.getTransaction().commit();
        }

    }
}
