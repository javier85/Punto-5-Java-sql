/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.*;
import Model.*;
import java.util.LinkedList;

/**
 *
 * @author carloslondono
 */
public class ctlPet {

    private modelDog modelDog;
    private modelCat modelCat;
    public ctlPet() {
        this.modelDog = new modelDog();
        this.modelCat = new modelCat();
    }

    public boolean CreatePet(clsPet pet) {
        try {
            switch (pet.getAnimalType()) {
                case "Cat":

                    break;
                case "Dog":
                    this.modelDog.CreatePet((clsDog)pet);
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean EditPet(clsPet pet) {

        try {
            switch (pet.getAnimalType()) {
                case "Cat":

                    break;
                case "Dog":
                    this.modelDog.EditPet((clsDog)pet);
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean DeletePet(clsPet pet) {

        try {
            switch (pet.getAnimalType()) {
                case "Gato":

                    break;
                case "Perro":
                    this.modelDog.DeletePet((clsDog)pet);
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public clsPet SearchPet(String code, String type) {
        clsPet pet = null;
        try {
            switch (type) {
                case "Cat":

                    break;
                case "Dog":
                    pet = this.modelDog.SearchPet(code);
                    break;
            }
            return pet;
        } catch (Exception e) {
            return null;
        }
    }
    
    public LinkedList<clsPet> ListPet(String type) {
        LinkedList<clsPet> petList = null;
        try {
            switch (type) {
                case "Cat":

                    break;
                case "Dog":
                    petList = this.modelDog.ListPet();
                    break;
            }
            return petList;
        } catch (Exception e) {
            return null;
        }
    }
}
