<!doctype html>
<%   
    request.setAttribute("idUsuario", null);
%>
<html>
    <head>
        <title>Sistema de Trazabilidad</title>
    <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta http-equiv="Expires" content="0"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            {
                if (history.forward(1))
                    location.replace(history.forward(1));
            }
        </script>
    </head>

    <body>
    <center>
        <div id="main">
            <div class="inner">

                <!-- Header -->
                <header id="header">
                    <a href="index.jsp" class="logo"><strong>Sistema de Trazabilidad</strong></a>
                    <ul class="icons">
                        <li>
                             <form id="form" method="POST" action="Inicio_sesion.jsp" >
                                <button title="Iniciar sesion" >iniciar sesion</button>
                            </form>
                        </li>

                    </ul>
                </header>

                
                <div class=content>
                    <section id="banner1">

                        <div>

                            <h2 >Proyecto</h2>
                            <p>Los sistemas de gestión, utilizados por almacenes y grades superficies, que se encargan del control de alimentos y medicamentos realizan periódica y manualmente la revisión de las fechas de vencimiento y la ubicación de forma física, adicionalmente al momento de realizar la venta no existe una alerta en caso de que algún producto ya no sea seguro para el consumo. Teniendo en cuenta lo anterior, se plantea la construcción de una aplicación software, que facilite la administración, gestión y control de los diferentes productos almacenados, mediante la notificación previa a través de dispositivos móviles en el momento que se detecte la proximidad de vencimiento de los diferentes productos. </p>
                        </div>         

                    </section>
                </div>
            </div> 
        </div>  
    </center>
</body>
</html>
