/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Classes.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author carloslondono
 */
public class modelDog {

    DbData dbData;

    public modelDog() {
        this.dbData = new DbData();
    }

    public boolean CreatePet(clsDog dog) {
        try (Connection conn = DriverManager.getConnection(dbData.getUrl(), dbData.getUser(), dbData.getPassword())) {
            String query = "INSERT INTO tb_pet (code, name, born_year, color, health_status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statementPet = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statementPet.setString(1, dog.getCode());
            statementPet.setString(2, dog.getName());
            statementPet.setInt(3, dog.getBorn_year());
            statementPet.setString(4, dog.getColor());
            statementPet.setString(5, dog.getHealth_status());
            int rowsInserted = statementPet.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statementPet.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idPet = generatedKeys.getInt(1);
                    query = "INSERT INTO tb_dog (breed, pedigree, id_pet) VALUES (?, ?, ?)";
                    PreparedStatement statementDog = conn.prepareStatement(query);
                    statementDog.setString(1, dog.getBreed());
                    statementDog.setBoolean(2, dog.isPedigree());
                    statementDog.setInt(3, idPet);
                    rowsInserted = statementDog.executeUpdate();
                    if (rowsInserted > 0) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean EditPet(clsDog dog) {
        try (Connection conn = DriverManager.getConnection(dbData.getUrl(), dbData.getUser(), dbData.getPassword())) {
            String queryPet = "UPDATE tb_pet SET code = ?, name = ?, born_year = ?, color = ?, health_status = ? WHERE id = ?";
            PreparedStatement statementPet = conn.prepareStatement(queryPet);
            statementPet.setString(1, dog.getCode());
            statementPet.setString(2, dog.getName());
            statementPet.setInt(3, dog.getBorn_year());
            statementPet.setString(4, dog.getColor());
            statementPet.setString(5, dog.getHealth_status());
            statementPet.setInt(6, dog.getPetId());

            String queryDog = "UPDATE tb_dog SET breed = ?, pedigree = ? WHERE id = ?";
            PreparedStatement statementDog = conn.prepareStatement(queryDog);
            statementDog.setString(1, dog.getBreed());
            statementDog.setBoolean(2, dog.isPedigree());
            statementDog.setInt(3, dog.getDogId());
            int rowsUpdatedPet = statementPet.executeUpdate();
            int rowsUpdatedDog = statementDog.executeUpdate();
            return rowsUpdatedPet > 0 && rowsUpdatedDog > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean DeletePet(clsPet pet) {

        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public clsPet SearchPet(String code) {
        clsDog dog = null;
        try (Connection conn = DriverManager.getConnection(dbData.getUrl(), dbData.getUser(), dbData.getPassword())) {
            String query = "SELECT * FROM tb_pet AS p INNER JOIN tb_dog AS d on p.id = d.id_pet WHERE p.code = ?";
            PreparedStatement statementPet = conn.prepareStatement(query);
            statementPet.setString(1, code);
            ResultSet result = statementPet.executeQuery();
            while (result.next()) {
                int petId = result.getInt(1);
                String petCode = result.getString(2);
                String petName = result.getString(3);
                int petBornYear = result.getInt(4);
                String petColor = result.getString(5);
                String petHealthStatus = result.getString(6);
                int dogId = result.getInt(7);
                String petBreed = result.getString(8);
                Boolean petPedigree = result.getBoolean(9);
                dog = new clsDog(dogId, petBreed, petPedigree, petId, petCode, petName, petBornYear, petColor, petHealthStatus);
            }
            return dog;
        } catch (Exception e) {
            return dog;
        }
    }
    
    
    public LinkedList<clsPet> ListPet() {
        LinkedList<clsPet> dogList = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbData.getUrl(), dbData.getUser(), dbData.getPassword())) {
            String query = "SELECT * FROM tb_pet AS p INNER JOIN tb_dog AS d on p.id = d.id_pet";
            PreparedStatement statementPet = conn.prepareStatement(query);
            ResultSet result = statementPet.executeQuery();
            while (result.next()) {
                int petId = result.getInt(1);
                String petCode = result.getString(2);
                String petName = result.getString(3);
                int petBornYear = result.getInt(4);
                String petColor = result.getString(5);
                String petHealthStatus = result.getString(6);
                int dogId = result.getInt(7);
                String petBreed = result.getString(8);
                Boolean petPedigree = result.getBoolean(9);
                clsDog dog = new clsDog(dogId, petBreed, petPedigree, petId, petCode, petName, petBornYear, petColor, petHealthStatus);
                dogList.add(dog);
            }
            return dogList;
        } catch (Exception e) {
            return dogList;
        }
    }
}
