import React from 'react';
import { jsPDF } from 'jspdf';

function GeneratePDF({ invoiceData }) {

    const generatePdf = async () => {
        const doc = new jsPDF();

        // Obtener el ancho de la página
        const pageWidth = doc.internal.pageSize.getWidth();

        // Establecer el color de fondo
        doc.setFillColor(152, 108, 52); // RGB para el color de fondo (puedes cambiarlo a marrón si lo necesitas)
        doc.rect(0, 10, pageWidth, 20, 'F');  // Dibuja un rectángulo que ocupa todo el ancho (x, y, width, height)

        // Cargar imagen (logo)
        const img = new Image();
        img.src = '/logo/logo_variant.png'; // Ruta relativa a la carpeta public

        img.onload = () => {
            // Obtener el ancho de la imagen
            const imgWidth = 50; // Ancho de la imagen en puntos (ajusta según sea necesario)
            const imgHeight = 20; // Alto de la imagen en puntos (ajusta según sea necesario)

            // Calcular la posición X para centrar la imagen
            const x = (pageWidth - imgWidth) / 2; // Centro horizontalmente
            const y = 10; // Posición vertical (ajusta según sea necesario)

            // Agregar logotipo centrado
            doc.addImage(img, 'PNG', x, y, imgWidth, imgHeight); // x, y, width, height

            // Título del documento
            doc.setFontSize(18);
            doc.text('Factura Veterinaria', (pageWidth / 2), 40, { align: 'center' }); // Título centrado horizontalmente

            // Línea divisoria debajo del título
            doc.setLineWidth(0.5);
            doc.line(10, 50, pageWidth - 10, 50); // Línea divisoria completa

            // Información de la factura
            doc.setFontSize(12);
            doc.text(`Fecha: ${invoiceData.invoiceDate}`, 160, 60); // Ubicada en la esquina derecha

            // Información del veterinario, dueño y mascota
            doc.setFontSize(12);
            doc.text(`Veterinario: ${invoiceData.veterinarianName}`, 20, 70);
            doc.text(`Dueño: ${invoiceData.ownerName}`, 20, 80);
            doc.text(`Mascota: ${invoiceData.petName}`, 20, 90);

            // Línea divisoria debajo de la información de la consulta
            doc.line(10, 95, pageWidth - 10, 95);

            // Costo total
            doc.setFontSize(14);
            doc.text(`Costo total: $${invoiceData.totalCost}`, 20, 105);

            // Footer o mensaje adicional (opcional)
            doc.setFontSize(10);
            doc.text('Gracias por confiar en nosotros', 20, 130);

            // Descargar el PDF
            doc.save('factura_veterinaria.pdf');
        };
    };

    return (
        <div>
            <button onClick={generatePdf} className="btn btn-xs bg-orange-400 text-white italic">Descargar Factura</button>
        </div>
    );
}

export default GeneratePDF;


