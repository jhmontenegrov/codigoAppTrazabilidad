/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr;

import beans.Almacen;
import beans.Inventario;
import beans.Producto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class QRGenerador {
    public void qrGenera(Producto producto, Inventario inventario, Almacen almacen) {
        try {
            String content7 = producto.getProductoID();
            String nomproducto = producto.getNomproducto();
            String dateEla = producto.getFechlaboracion()+"";
            String dateVto= producto.getFechvencimiento()+"";
            String marcaP= producto.getMarca();
            String nlote = producto.getLote();
            String precio = producto.getVlorproducto()+"";
            String cant = inventario.getCantidad()+"";
            String zone = inventario.getZona()+"";
            String estante = inventario.getEstante()+"";
            String alcen = almacen.getUbicacion();
            
            String fileType = "png";
            String archivo = "C:\\CodigoQR/" + content7 + "." + fileType;
            int size = 160;
            QRCodeWriter qrcode = new QRCodeWriter();
            BitMatrix matrix = qrcode.encode("Id: "+content7+"\n"+"Nombre: "+nomproducto+"\n"+"Fecha Vencimiento: "+dateVto+"\n"
                    +"Marca: "+marcaP+"\n"+"Lote: "+nlote+"\n"+"Precio: "+precio+"\n"+"Cantidad: "+cant+"\n"+"Zona: "+zone+"\n"+"Estante: "+estante+"\n"+"Almacen: "+alcen, BarcodeFormat.QR_CODE, size, size);
            File qrFile = new File(archivo);
            int matrixWidth = matrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            graphics.setColor(Color.BLACK);
            for (int b = 0; b < matrixWidth; b++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (matrix.get(b, j)) {
                        graphics.fillRect(b, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, qrFile);
        } catch (WriterException | IOException ex) {
            Logger.getLogger(QRGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
