package Entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceApp {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entidades");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Domicilio domicilio = Domicilio.builder().nombreCalle("Saenz Peña").numero(1000).build();
            Cliente cliente = Cliente.builder()
                    .nombre("Camila")
                    .apellido("Manita").domicilio(domicilio).build();
            domicilio.setCliente(cliente);
            Factura factura1 = Factura.builder().numero(12).fecha("10/08/2024").cliente(cliente).build();
            Categoria perecederos = Categoria.builder().denominacion("perecederos").build();
            Categoria limpieza = Categoria.builder().denominacion("limpieza").build();
            Categoria lacteos = Categoria.builder().denominacion("lacteos").build();
            Articulo articulo1 = Articulo.builder().precio(200).denominacion("Leche Larga vida").cantidad(2).build();
            Articulo articulo2 = Articulo.builder().precio(300).denominacion("Lavandina").cantidad(1).build();
            articulo1.getCategorias().add(perecederos);
            articulo1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(articulo1);
            perecederos.getArticulos().add(articulo1);
            articulo2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo2);

            DetalleFactura det1 = DetalleFactura.builder().build();
            det1.setArticulo(articulo1);
            det1.setCantidad(2);
            det1.setSubtotal(40);
            det1.setFactura(factura1);

            DetalleFactura det2 = DetalleFactura.builder().build();
            det2.setArticulo(articulo2);
            det2.setCantidad(2);
            det2.setSubtotal(80);
            det2.setFactura(factura1);
            factura1.setTotal(120);
            entityManager.persist(factura1);
            entityManager.persist(det1);
            entityManager.persist(det2);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Cliente");
        }

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
