<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
<%@page import ="beans.Conexion" %>
<%@page import="java.sql.Statement"%>
<%
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setDateHeader("Expires", 0);
//recuperar la sesión
    HttpSession sesion_cli = request.getSession(true);
    Integer idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");    
    Connection conn = (Connection) Conexion.crearConexion();
    ResultSet resultaLog;
    Statement sent;
    sent = (Statement) conn.createStatement();
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_proveedor.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado1;
        Statement sentencia, sentencia1;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia1 = (Statement) con.createStatement();
        Integer idPr = Integer.parseInt(request.getParameter("Id"));
        resultado = sentencia.executeQuery("SELECT * FROM proveedor pr, ciudad c WHERE ProveedorID='" + idPr + "' AND c.ciudadID=pr.ciudadID");
        resultado1 = sentencia1.executeQuery("SELECT * FROM ciudad ci");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualiza Proveedor</title>
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <% while (resultado.next()) {
                String nom_proveedor = resultado.getString("pr.Nom_proveedor");
                String dir_proveedor = resultado.getString("pr.Dir_proveedor");
                String tel_proveedor = resultado.getString("pr.Tel_proveedor");
                String mail_proveedor = resultado.getString("pr.Mail_proveedor");
                Integer idpr = resultado.getInt("pr.ProveedorID");
                Integer idciu = resultado.getInt("pr.ciudadID");
                String nombciu = resultado.getString("c.ciudad_nombre");
                String paisciu = resultado.getString("c.pais");
        %>
        <form id="form1" method="POST" action="ActualizarProveedorCtrl">
            <table style="width: 100%">
                <tr>

                <input type="hidden" name="idpr" value="<%=idpr%>"/>

                <td>
                    <label for="nombreProv">Nombre de proveedor</label>
                    <input type="text" name="nombreProv" value="<%=nom_proveedor%>" disabled/>
                </td>
                <td>
                    <label for="direccionProv">Dirección de proveedor</label>
                    <input type="text" name="direccionProv" value="<%=dir_proveedor%>" required pattern="[a-z0-9 |-]{3,31}" title="No corresponde a una dirección válida"/>
                </td>
                <td>
                    <label for="telProv">telefono de proveedor:</label>
                    <input type="text" name="telProv" value="<%=tel_proveedor%>" required pattern="[0-9]{7,10}" title="Ingrese un número de contacto valido"/>
                </td>
                </tr>
                <tr>
                    <td>
                        <label for="mailProv">E-mail de proveedor:</label>
                        <input type="email" name="mailProv" value="<%=mail_proveedor%>" required/>
                    </td>
                    <td>
                        <label for="id_ciudadAC">Ciudad de proveedor</label>
                        <select name="id_ciudadAC" required>
                            <option value="<%=idciu%>"><%=nombciu%> - <%=paisciu%></option>
                            <%}
                                while (resultado1.next()) {
                                    Integer id_ciudad = resultado1.getInt("ci.CiudadID");
                                    String ciudad = resultado1.getString("ci.Ciudad_nombre");
                                    String ciudadPais = resultado1.getString("ci.Pais");
                            %>
                            <option value=<%=id_ciudad%> ><%=ciudad%> - <%=ciudadPais%></option>
                            <%}%>
                        </select>
                    </td>
                    <td>
                        <br>
                        <button title="Actualizar">Actualizar</button>
                    </td>
                </tr>
            </table>
        </form>
    </body><%}%>