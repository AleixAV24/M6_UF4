from cliente import db_client
from datetime import datetime

def read():
    try:
        conn = db_client()
        cur = conn.cursor()
        cur.execute("SELECT * FROM product")

        result = cur.fetchall()
    except Exception as e:
        return {"status": -1, "message": f"Error de conexión{e}"}
    finally: 
        conn.close()

    return result

def read_one(id: int):
    try:
        conn = db_client()
        cur = conn.cursor()
        cur.execute("SELECT * FROM product WHERE product_id = %s", (id,))
        result = cur.fetchone()
    except Exception as e:
        return {"status": -1, "message": f"Error de conexión: {e}"}
    finally:
        conn.close()
    return result

def post(name, description, company, price, units, subcategory_id):
    try:
        conn = db_client()
        cur = conn.cursor()

        query = "INSERT INTO product (name, description, company, price, units, subcategory_id) VALUES (%s, %s, %s, %s, %s, %s)"

        values = (name, description, company, price, units, subcategory_id) 

        cur.execute(query, values)
        product_id = cur.lastrowid
        conn.commit()

    except Exception as e:
        return {"status": -1, "message": f"Error de conexión: {e}"}
    finally: 
        conn.close()

    return product_id

def put(product_id, units):
    try:
        conn = db_client()
        cur = conn.cursor()

        query = "update product SET units = %s WHERE product_id = %s;"

        values=(units, product_id,)
        cur.execute(query,values)
        updated_recs = cur.rowcount
    
        conn.commit()
    
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió:{e}" }
    
    finally:
        conn.close()

    return updated_recs

def delete(id):
    try:
        conn = db_client()
        cur = conn.cursor()

        query = "DELETE FROM product WHERE product_id = %s;"
        cur.execute(query,(id,))
        deleted_recs = cur.rowcount
        conn.commit()
    
    except Exception as e:
        return {"status": -1, "message": f"Error de connexió:{e}" }
    
    finally:
        conn.close()
        
    return deleted_recs

def getAllProducts():
    try: 
        conn = db_client()
        cur = conn.cursor()

        query = """
        SELECT c.name as category_name, sc.name as subcategory_name, p.name as product_name, p.company, p.price
        FROM category c
        JOIN subcategory sc ON c.category_id = sc.category_id
        JOIN product p ON sc.subcategory_id = p.subcategory_id;
        """

        cur.execute(query)
        result = cur.fetchall()
    except Exception as e:
        return {"status": -1, "message": f"Error de conexión: {e}"}
    finally: 
        if cur is not None:
            cur.close()
        if conn is not None:
            conn.close()

    return result

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

def products_schema(products) -> dict:
    return [product_schema(product) for product in products]

def category_schema(category):
    return {
        "category_id": category[0],
        "name": category[1],
        "created_at": category[2],
        "updated_at": category[3]
    }

def subcategory_schema(subcategory):
    return {
        "subcategory_id": subcategory[0],
        "name": subcategory[1],
        "category_id": subcategory[2],
        "created_at": subcategory[3],
        "updated_at": subcategory[4]
    }

def categories_schema(categories) -> dict:
    return [category_schema(category) for category in categories]

def subcategories_schema(subcategories) -> dict:
    return [subcategory_schema(subcategory) for subcategory in subcategories]

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

def insert_or_update_category(category_id, name):
    conn = db_client()
    cur = conn.cursor()
    try:
        cur.execute("SELECT * FROM category WHERE category_id = %s", (category_id,))
        existing_category = cur.fetchone()
        if existing_category:
            cur.execute("UPDATE category SET name = %s, updated_at = %s WHERE category_id = %s", (name, datetime.now(), category_id))
        else:
            cur.execute("INSERT INTO category (category_id, name, created_at, updated_at) VALUES (%s, %s, %s, %s)", (category_id, name, datetime.now(), datetime.now()))
        conn.commit()
    finally:
        conn.close()

def insert_or_update_subcategory(subcategory_id, name, category_id):
    conn = db_client()
    cur = conn.cursor()
    try:
        cur.execute("SELECT * FROM subcategory WHERE subcategory_id = %s", (subcategory_id,))
        existing_subcategory = cur.fetchone()
        if existing_subcategory:
            cur.execute("UPDATE subcategory SET name = %s, category_id = %s, updated_at = %s WHERE subcategory_id = %s", (name, category_id, datetime.now(), subcategory_id))
        else:
            cur.execute("INSERT INTO subcategory (subcategory_id, name, category_id, created_at, updated_at) VALUES (%s, %s, %s, %s, %s)", (subcategory_id, name, category_id, datetime.now(), datetime.now()))
        conn.commit()
    finally:
        conn.close()

def insert_or_update_product(product_id, name, description, company, price, units, subcategory_id):
    conn = db_client()
    cur = conn.cursor()
    try:
        cur.execute("SELECT * FROM product WHERE product_id = %s", (product_id,))
        existing_product = cur.fetchone()
        if existing_product:
            cur.execute("UPDATE product SET name = %s, description = %s, company = %s, price = %s, units = %s, subcategory_id = %s, updated_at = %s WHERE product_id = %s", (name, description, company, price, units, subcategory_id, datetime.now(), product_id))
        else:
            cur.execute("INSERT INTO product (product_id, name, description, company, price, units, subcategory_id, created_at, updated_at) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)", (product_id, name, description, company, price, units, subcategory_id, datetime.now(), datetime.now()))
        conn.commit()
    finally:
        conn.close()

