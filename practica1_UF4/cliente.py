import mysql.connector

# Funció per establir la connexió amb la base de dades
def db_client():
    try:
        # Configuració dels paràmetres de connexió
        dbname = "botiga"  # Nom de la base de dades
        user = 'dam_app'   # Usuari de la base de dades
        password = '1234'  # Contrasenya de la base de dades
        host = 'localhost' # Host on es troba la base de dades
        port = 3306        # Port de la base de dades

        # Establim la connexió amb la base de dades
        conn = mysql.connector.connect(
            host=host,
            port=port,
            user=user,
            password=password,
            database=dbname
        )

        # Retornem la connexió establerta
        return conn
    except Exception as e:
        # Si hi ha un error en la connexió, retornem un missatge d'error
        return {"status": -1, "message": f"Error de connexió: {e}"}
