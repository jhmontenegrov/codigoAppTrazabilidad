<%-- 
    Document   : inicio
    Created on : 11-nov-2018, 11:05:45
    Author     : jhona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/main.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script src='push.js/push.min.js'></script>
        <title>Ventas</title>
    </head>
    <body>
        
        <form name="form2" action="RegistroVenta" method="POST">
            <table>
                <td>
                    <label for="idProducto">Id del producto</label>
                    <input type="text" name="idProducto" required pattern="[a-z0-9]{3-8}"
                 title="Por favor, valide la información ingresada, recuerde que debe llenar el campo con letras en minúscula y números"/>
                </td>
                <td>
                    <label for="cantidad">Cantidad</label>
                    <input type="number" name="cantidad" required min="1" pattern="[0-9]" title="Por favor, valide la información ingresada"/>
                </td>
                <td><input type="submit" name="bto" value="Añadir"/></td>

            </table>

        </form>
        <iframe name="cargapa" class="cargaP1" src="listaCompra.jsp"></iframe>

    </body>
</html>
