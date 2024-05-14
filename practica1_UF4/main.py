import botiga_db
from typing import Union
from fastapi import FastAPI, HTTPException, UploadFile, File
from pydantic import BaseModel
from io import StringIO 
import csv

class ProductData(BaseModel):
    name: str
    description: str
    company: str
    price: float
    units: int
    subcategory_id: int


class ProductUpdate(BaseModel):
    units: int

app = FastAPI()

@app.get("/product/")
def red_productos():
    return botiga_db.products_schema(botiga_db.read())

@app.get("/product/{item_id}/")
def read_products_id(item_id: int): 
    return botiga_db.product_schema(botiga_db.read_one(item_id)) 

@app.post("/product/")
async def create_product(data: ProductData):
    product_id = botiga_db.post(data.name, data.description, data.company, data.price, data.units, data.subcategory_id)
    return {
        "msg": "Producto añadido exitosamente",
        "id": product_id,
        "name": data.name
    }

@app.put("/product/{product_id}")
def update_product(product_id: int, data: ProductUpdate):
    updated_records = botiga_db.put(product_id, data.units)
    if updated_records == 0:
        raise HTTPException(status_code=404, detail="Items to update not found")
    return {"msg": "S'ha modificat correctament"}

@app.delete("/product/{id}")
def delete_film(id:int):
    deleted_records = botiga_db.delete(id)
    if deleted_records == 0:
       raise HTTPException(status_code=404, detail="Items to delete not found") 
    
@app.get("/productAll/")
def get_all_products():
    result = botiga_db.getAllProducts()
    if isinstance(result, dict) and result.get("status") == -1:
        raise HTTPException(status_code=500, detail=result.get("message"))
    
    formatted_results = [
        {
            "nom_categoria": product[0],
            "nom_subcategoria": product[1],
            "nom_producte": product[2],
            "marca_company": product[3],
            "preu": product[4]
        }
        for product in result
    ]
    
    return formatted_results

@app.post("/loadProducts/")
async def load_products(file: UploadFile = File(...)):
    try:
        decoded_file = file.file.read().decode("utf-8").splitlines()
        reader = csv.DictReader(decoded_file)
        for row in reader:
            category_id = int(row["id_categoria"])
            category_name = row["nom_categoria"]
            subcategory_id = int(row["id_subcategoria"])
            subcategory_name = row["nom_subcategoria"]
            product_id = int(row["id_producto"])
            product_name = row["nom_producto"]
            description = row["descripcion_producto"]
            company = row["companyia"]
            price = float(row["precio"])
            units = int(row["unidades"])

            botiga_db.insert_or_update_category(category_id, category_name)
            botiga_db.insert_or_update_subcategory(subcategory_id, subcategory_name, category_id)
            botiga_db.insert_or_update_product(product_id, product_name, description, company, price, units, subcategory_id)
        return {"status": "success", "message": "Càrrega massiva de productes realitzada amb èxit."}
    except Exception as e:
        return {"status": "error", "message": str(e)}