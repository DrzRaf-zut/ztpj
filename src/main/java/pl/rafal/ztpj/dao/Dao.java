package pl.rafal.ztpj.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    boolean save(T t);

    boolean delete(T t);

    void writeToFile(List<T> t, String destination) throws IOException;

    List<T> readFile(String source) throws IOException, ClassNotFoundException;
}