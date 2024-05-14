from cliente import db_client
from datetime import datetime

# Funció per obtenir tots els productes de la base de dades
def read():
    try:
        conn = db_client()  # Obrim una connexió a la base de dades
        cur = conn.cursor()
        cur.execute("SELECT * FROM product")  # Executem la consulta per obtenir tots els productes
        result = cur.fetchall()  # Recuperem tots els productes de la consulta
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió: {e}"}
    finally:
        conn.close() 

    return result

# Funció per obtenir un producte específic pel seu ID
def read_one(id: int):
    try:
        conn = db_client()  
        cur = conn.cursor()
        cur.execute("SELECT * FROM product WHERE product_id = %s", (id,))  # Executem la consulta per obtenir el producte per ID
        result = cur.fetchone()  # Recuperem el producte de la consulta
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió: {e}"}
    finally:
        conn.close()  

    return result  

# Funció per afegir un nou producte a la base de dades
def post(name, description, company, price, units, subcategory_id):
    try:
        conn = db_client()  
        cur = conn.cursor()

        # Definim la consulta SQL per inserir un nou producte
        query = "INSERT INTO product (name, description, company, price, units, subcategory_id) VALUES (%s, %s, %s, %s, %s, %s)"
        values = (name, description, company, price, units, subcategory_id)

        cur.execute(query, values)  
        product_id = cur.lastrowid  # Obtenim l'ID del nou producte inserit
        conn.commit() 
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió: {e}"}
    finally:
        conn.close()  

    return product_id  # Retornem l'ID del nou producte

# Funció per actualitzar les unitats d'un producte existent
def put(product_id, units):
    try:
        conn = db_client()  
        cur = conn.cursor()

        # Definim la consulta SQL per actualitzar les unitats d'un producte
        query = "UPDATE product SET units = %s WHERE product_id = %s"
        values = (units, product_id)

        cur.execute(query, values)  
        updated_recs = cur.rowcount  
        conn.commit() 
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió: {e}"}
    finally:
        conn.close()  

    return updated_recs  # Retornem el nombre de registres actualitzats

# Funció per eliminar un producte de la base de dades
def delete(id):
    try:
        conn = db_client() 
        cur = conn.cursor()

        # Definim la consulta SQL per eliminar un producte
        query = "DELETE FROM product WHERE product_id = %s"
        cur.execute(query, (id,))  
        deleted_recs = cur.rowcount  # Comptem quantes files s'han eliminat
        conn.commit()  
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió: {e}"}
    finally:
        conn.close() 

    return deleted_recs  # Retornem el nombre de registres eliminats

# Funció per obtenir informació detallada de tots els productes
def getAllProducts():
    try:
        conn = db_client()  
        cur = conn.cursor()

        # Definim la consulta SQL per obtenir informació detallada de tots els productes
        query = """
        SELECT c.name as category_name, sc.name as subcategory_name, p.name as product_name, p.company, p.price
        FROM category c
        JOIN subcategory sc ON c.category_id = sc.category_id
        JOIN product p ON sc.subcategory_id = p.subcategory_id;
        """

        cur.execute(query)  
        result = cur.fetchall()  
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió: {e}"}
    finally:
        if cur:
            cur.close()  
        if conn:
            conn.close()  

    return result  

# Funció per convertir un producte a un diccionari
def product_schema(product):
    return {
        "product_id": product[0],
        "name": product[1],
        "description": product[2],
        "company": product[3],
        "price": product[4],
        "units": product[5],
        "subcategory_id": product[6],
        "created_at": product[7],
        "updated_at": product[8]
    }

# Funció per convertir una llista de productes a una llista de diccionaris
def products_schema(products) -> dict:
    return [product_schema(product) for product in products]

# Funció per convertir una categoria a un diccionari
def category_schema(category):
    return {
        "category_id": category[0],
        "name": category[1],
        "created_at": category[2],
        "updated_at": category[3]
    }

# Funció per convertir una subcategoria a un diccionari
def subcategory_schema(subcategory):
    return {
        "subcategory_id": subcategory[0],
        "name": subcategory[1],
        "category_id": subcategory[2],
        "created_at": subcategory[3],
        "updated_at": subcategory[4]
    }

# Funció per convertir una llista de categories a una llista de diccionaris
def categories_schema(categories) -> dict:
    return [category_schema(category) for category in categories]

# Funció per convertir una llista de subcategories a una llista de diccionaris
def subcategories_schema(subcategories) -> dict:
    return [subcategory_schema(subcategory) for subcategory in subcategories]

# Funció per convertir una llista de productes a una llista de diccionaris amb informació detallada
def products_all_schema(products):
    return [
        {
            "category_name": product[0],
            "subcategory_name": product[1],
            "product_name": product[2],
            "company": product[3],
            "price": product[4]
        }
        for product in products
    ]

# Funció per inserir o actualitzar una categoria
def insert_or_update_category(category_id, name):
    conn = db_client() 
    cur = conn.cursor()
    try:
        cur.execute("SELECT * FROM category WHERE category_id = %s", (category_id,))
        existing_category = cur.fetchone()
        if existing_category:
            # Actualitzem la categoria si ja existeix
            cur.execute("UPDATE category SET name = %s, updated_at = %s WHERE category_id = %s", (name, datetime.now(), category_id))
        else:
            # Inserim una nova categoria si no existeix
            cur.execute("INSERT INTO category (category_id, name, created_at, updated_at) VALUES (%s, %s, %s, %s)", (category_id, name, datetime.now(), datetime.now()))
        conn.commit()  
    finally:
        conn.close()  

# Funció per inserir o actualitzar una subcategoria
def insert_or_update_subcategory(subcategory_id, name, category_id):
    conn = db_client()  # Obrim una connexió a la base de dades
    cur = conn.cursor()
    try:
        cur.execute("SELECT * FROM subcategory WHERE subcategory_id = %s", (subcategory_id,))
        existing_subcategory = cur.fetchone()
        if existing_subcategory:
            # Actualitzem la subcategoria si ja existeix
            cur.execute("UPDATE subcategory SET name = %s, category_id = %s, updated_at = %s WHERE subcategory_id = %s", (name, category_id, datetime.now(), subcategory_id))
        else:
            # Inserim una nova subcategoria si no existeix
            cur.execute("INSERT INTO subcategory (subcategory_id, name, category_id, created_at, updated_at) VALUES (%s, %s, %s, %s, %s)", (subcategory_id, name, category_id, datetime.now(), datetime.now()))
        conn.commit()  
    finally:
        conn.close()  

# Funció per inserir o actualitzar un producte
def insert_or_update_product(product_id, name, description, company, price, units, subcategory_id):
    conn = db_client() 
    cur = conn.cursor()
    try:
        cur.execute("SELECT * FROM product WHERE product_id = %s", (product_id,))
        existing_product = cur.fetchone()
        if existing_product:
            # Actualitzem el producte si ja existeix
            cur.execute("UPDATE product SET name = %s, description = %s, company = %s, price = %s, units = %s, subcategory_id = %s, updated_at = %s WHERE product_id = %s", (name, description, company, price, units, subcategory_id, datetime.now(), product_id))
        else:
            # Inserim un nou producte si no existeix
            cur.execute("INSERT INTO product (product_id, name, description, company, price, units, subcategory_id, created_at, updated_at) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)", (product_id, name, description, company, price, units, subcategory_id, datetime.now(), datetime.now()))
        conn.commit() 
    finally:
        conn.close() 
