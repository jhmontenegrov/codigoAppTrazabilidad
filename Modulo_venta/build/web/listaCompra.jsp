<%-- 
    Document   : listaCompra
    Created on : 11-nov-2018, 16:13:51
    Author     : jhona
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%@page import="Modelo.ListaProductos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String ven = "";
    String a=null;
    HttpSession sesion = request.getSession();
    String ven1 = (String) session.getAttribute("ven");
    if (ven1.equals(null)) {
        ven = "nVencido";
    } else {
        ven = ven1;
    }
    ListaProductos lPro = null;
    List as = new ArrayList();
    double p = 0;
    lPro = (ListaProductos) sesion.getAttribute("bLista");
%>
<html>
    <head>
        <link rel="stylesheet" href="css/main.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script src='push.js/push.min.js'></script>
        <title>Lista compra</title>
    </head>
    <body>
        <div>
            <%
                if (lPro == null) {
            %>
            <h3>No hay compra</h3>

            <%
            } else {%>
            <table>
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>MARCA</th>
                    <th>LOTE</th>
                    <th>PRECIO</th>
                    <th>FECHA DE VENCIMIENTO</th>
                </tr>

                <%
                    for (Producto pro : lPro.getLista()) {
                        p = pro.getVlorproducto();
                        a = pro.getProductoID();
                %>
                <tr>
                    <td><%=pro.getProductoID()%></td>
                    <td><%=pro.getNomproducto()%></td>
                    <td><%=pro.getMarca()%></td>
                    <td><%=pro.getLote()%></td>
                    <td><%=pro.getVlorproducto()%></td>
                    <td><%=pro.getFechvencimiento()%></td>
                </tr>
                <%as.add(p);
                    }
                    double ee = 0;
                    double es = 0;
                    for (int i = 0; i < as.size(); i++) {
                        String q = as.get(i).toString();
                        ee = Double.parseDouble(q);
                        es = ee + es;
                    }
                %>
            </table>
            <table>
                <tr>
                    <td>TOTAL</td>
                    <td><%=es%></td>
                </tr>
                <tr>
                    <td>
                        <form method="POST" action="IngresarVentCtrol">
                            <button>Finalizar Venta</button>
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
        <div>
            <% if (ven.equals(null)) {

                } else if (ven.equals("vencido")) {
            %>


            <script type="text/javascript">
                $.ajax({
                    async: true,
                    type: "POST",
                    url: "",
                    data1: "",
                    dataType: "html",
                    success: function (data1) {

                        $('#div').html(data1);
                        Push.create("ALERTA...", {
                            body: "Se ha vencido el producto que intenta registrar ",
                            icon: "img/Logo_USB.png",
                            timeout: 6000
                        });
                    }
                });
            </script>
            <%
                }
            %>

        </div>
    </body>
</html>
