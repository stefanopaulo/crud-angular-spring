package com.stefano.crudspring;

import com.stefano.crudspring.enums.CategoryEnum;
import com.stefano.crudspring.model.Course;
import com.stefano.crudspring.model.Lesson;
import com.stefano.crudspring.repository.CourseRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular + Spring");
			c.setCategory(CategoryEnum.FRONTEND);
			
			Lesson l = new Lesson();
			l.setName("Aula: OneToMany");
			l.setYoutubeUrl("Nb4uxLxdvxo");
			l.setCourse(c);
			
			c.getLessons().add(l);

			courseRepository.save(c);
		};
    }

}
