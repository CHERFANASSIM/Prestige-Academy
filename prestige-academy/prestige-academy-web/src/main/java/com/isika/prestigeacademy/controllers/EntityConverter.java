package com.isika.prestigeacademy.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Generic converter of jpa entities for jsf
 *
 * Converts the jpa instances to strings with this form: @ Converts from strings to instances searching by id in
 * database
 *
 * It is possible thanks to the fact that jpa requires all entity ids to
 * implement serializable
 *
 * Requires: - You must provide instance with name "entityManagerFactory" to be
 * injected - Remember to implement equals and hashCode in all your entity
 * classes !!
 *
 */
@ManagedBean
@RequestScoped
public class EntityConverter implements Converter {

    private static final char CHARACTER_SEPARATOR = '@';

    @ManagedProperty(value = "#{entityManagerFactory}")
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private static final String empty = "";

    @Override
    public Object getAsObject(FacesContext context, UIComponent c, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        int index = value.indexOf(CHARACTER_SEPARATOR);
        String clazz = value.substring(0, index);
        String idBase64String = value.substring(index + 1, value.length());
        EntityManager entityManager=null;
        try {
            Class entityClazz = Class.forName(clazz);
            Object id = convertFromBase64String(idBase64String);

            entityManager = entityManagerFactory.createEntityManager();
            Object object = entityManager.find(entityClazz, id);

            return object;

        } catch (ClassNotFoundException e) {
            throw new ConverterException("Jpa entity not found " + clazz, e);
        } catch (IOException e) {
            throw new ConverterException("Could not deserialize id of jpa class " + clazz, e);
        }finally{
            if(entityManager!=null){
                entityManager.close();
            }
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent c, Object value) {
        if (value == null) {
            return empty;
        }
        String clazz = value.getClass().getName();
        String idBase64String;
        try {
            idBase64String = convertToBase64String(entityManagerFactory.getPersistenceUnitUtil().getIdentifier(value));
        } catch (IOException e) {
            throw new ConverterException("Could not serialize id for the class " + clazz, e);
        }

        return clazz + CHARACTER_SEPARATOR + idBase64String;
    }

    // UTILITY METHODS, (Could be refactored moving it to another place)

    public static String convertToBase64String(Object o) throws IOException {
        return javax.xml.bind.DatatypeConverter.printBase64Binary(convertToBytes(o));
    }

    public static Object convertFromBase64String(String str) throws IOException, ClassNotFoundException {
        return convertFromBytes(javax.xml.bind.DatatypeConverter.parseBase64Binary(str));
    }

    public static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    public static Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

}
