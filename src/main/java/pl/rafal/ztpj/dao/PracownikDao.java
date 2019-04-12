package pl.rafal.ztpj.dao;

import pl.rafal.ztpj.ConnectionFactory;
import pl.rafal.ztpj.model.Dyrektor;
import pl.rafal.ztpj.model.Handlowiec;
import pl.rafal.ztpj.model.Pracownik;
import pl.rafal.ztpj.model.StanowiskoE;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PracownikDao implements Dao<Pracownik> {


    @Override
    public Optional<Pracownik> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Pracownik> getAll() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pracownik");
            List<Pracownik> pracownicy = new ArrayList<>();
            while (rs.next()) {
                Pracownik pracownik = extractPracownikFromResultSet(rs);
                pracownicy.add(pracownik);
            }
            stmt.close();
            rs.close();
            connection.close();
            return pracownicy;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Pracownik extractPracownikFromResultSet(ResultSet rs) throws SQLException {
        Pracownik pracownik;
        if (rs.getString("stanowisko").equals(StanowiskoE.DYREKTOR.name())) {
            pracownik = new Dyrektor(rs.getInt("dodatek_sluzbowy"), rs.getString("karta_sluzbowa"), rs.getInt(
                    "limit_kosztow"));
        } else if (rs.getString("stanowisko").equals(StanowiskoE.HANDLOWIEC.name())) {
            pracownik = new Handlowiec(rs.getInt("prowizja"), rs.getInt("limit_prowizji"));
        } else {
            throw new IllegalArgumentException();
        }
        pracownik.setId(rs.getInt("id"));
        pracownik.setPesel(rs.getLong("pesel"));
        pracownik.setImie(rs.getString("imie"));
        pracownik.setNazwisko(rs.getString("nazwisko"));
        pracownik.setWynagrodzenie(rs.getInt("wynagrodzenie"));
        pracownik.setTelefon(rs.getString("telefon"));

        return pracownik;
    }

    @Override
    public boolean save(Pracownik pracownik) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps;
            if (pracownik instanceof Dyrektor) {
                ps = buildDyrektorStatement(connection, (Dyrektor) pracownik);
            } else if (pracownik instanceof Handlowiec) {
                ps = buildHandlowiecStatement(connection, (Handlowiec) pracownik);
            } else {
                throw new IllegalArgumentException();
            }

            int i = ps.executeUpdate();
            ps.close();
            connection.close();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Pracownik pracownik) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM pracownik WHERE id=" + pracownik.getId());
            stmt.close();
            connection.close();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void writeToFile(List<Pracownik> lista, String destination) throws IOException {
        FileOutputStream filOut = new FileOutputStream(destination);
        ObjectOutputStream objOut = new ObjectOutputStream(filOut);

        objOut.writeObject(lista);
        objOut.close();
        filOut.close();
    }

    @Override
    public List<Pracownik> readFile(String source) throws IOException, ClassNotFoundException {
        FileInputStream filIn = new FileInputStream(source);
        ObjectInputStream objIn = new ObjectInputStream(filIn);

        List<Pracownik> lista = (List<Pracownik>) objIn.readObject();
        objIn.close();

        return lista;
    }

    private PreparedStatement buildDyrektorStatement(Connection connection, Dyrektor dyrektor) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO pracownik(pesel, imie, nazwisko, " +
                "stanowisko, wynagrodzenie, telefon, dodatek_sluzbowy, karta_sluzbowa, limit_kosztow)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);");

        ps.setLong(1, dyrektor.getPesel());
        ps.setString(2, dyrektor.getImie());
        ps.setString(3, dyrektor.getNazwisko());
        ps.setString(4,dyrektor.getStanowisko());
        ps.setInt(5, dyrektor.getWynagrodzenie());
        ps.setString(6, dyrektor.getTelefon());
        ps.setInt(7, dyrektor.getDodatek_sluzbowy());
        ps.setString(8, dyrektor.getKarta_sluzbowa());
        ps.setInt(9, dyrektor.getLimit_kosztow());


        return ps;
    }

    private PreparedStatement buildHandlowiecStatement(Connection connection, Handlowiec handlowiec) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO pracownik(pesel, imie, nazwisko, " +
                "stanowisko, wynagrodzenie, telefon, prowizja, limit_prowizji)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?);");

        ps.setLong(1, handlowiec.getPesel());
        ps.setString(2, handlowiec.getImie());
        ps.setString(3, handlowiec.getNazwisko());
        ps.setString(4,handlowiec.getStanowisko());
        ps.setInt(5, handlowiec.getWynagrodzenie());
        ps.setString(6, handlowiec.getTelefon());
        ps.setInt(7, handlowiec.getProwizja());
        ps.setInt(8, handlowiec.getLimit_prowizji());

        return ps;
    }
}