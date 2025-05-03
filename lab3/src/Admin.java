package main.java.lab_3;

public class ContentManager extends User {
    public ContentManager(String login, String secret) {
        super(login, secret);
    }

    public void uploadFilm(MediaLibrary library, Movie film) {
        System.out.println("����� \"" + film.getFilmTitle() + "\" ������� �������� � ���������: " + library.getLibraryName());
    }
}	