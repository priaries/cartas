import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Random;

public class Carta {

    private int indice;

    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }

    public void mostrar(JPanel pnl, int x, int y) {
        String nombreImagen = "/imagenes/CARTA" + String.valueOf(indice) + ".jpg";
        ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen));
        JLabel lbl = new JLabel(imagen);
        lbl.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());
        pnl.add(lbl);
    }

    

    public Pinta getPinta() {
        if (indice <= 13)
            return Pinta.TREBOL;
        else if (indice <= 26)
            return Pinta.PICA;
        else if (indice <= 39)
            return Pinta.CORAZON;
        else
            return Pinta.DIAMANTE;
    }

    public NombreCarta getNombre() {
        int residuo = indice % 13;
        if (residuo == 0)
            residuo = 13;
        return NombreCarta.values()[residuo - 1];
    }
}
