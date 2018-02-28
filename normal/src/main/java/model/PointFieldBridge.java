package model;

import com.vividsolutions.jts.geom.Point;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleDocValuesField;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.util.BytesRef;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.MetadataProvidingFieldBridge;
import org.hibernate.search.bridge.StringBridge;
import org.hibernate.search.bridge.spi.FieldMetadataBuilder;
import org.hibernate.search.bridge.spi.FieldType;
import org.hibernate.search.elasticsearch.bridge.spi.Elasticsearch;
import org.hibernate.search.elasticsearch.cfg.DynamicType;
import org.hibernate.search.spatial.impl.SpatialNumericDocValueField;

public class PointFieldBridge implements StringBridge {

    public String objectToString(Object o) {
        return ((Double)((Point)o).getX()).toString();
    }
}
//public class PointFieldBridge implements MetadataProvidingFieldBridge {
//    public void configureFieldMetadata(String s, FieldMetadataBuilder fieldMetadataBuilder) {
//        fieldMetadataBuilder
//                .field( "_firstName", FieldType.DOUBLE )
//                .mappedOn(Elasticsearch.class)
//                .dynamic(DynamicType.TRUE)
//                .sortable( true );
////                .field(  "_middleName", FieldType.DOUBLE )
////                .sortable( true );
//    }
//
//    public void set(String s, Object o, Document document, LuceneOptions luceneOptions) {
//
//        Point point = (Point) o;
//        Double x = point.getX();
//        luceneOptions.addFieldToDocument("_firstName", x.toString(), document );
//        document.add( new DoubleDocValuesField( "_firstName", x));
//
//        System.out.println("............................... " + s);
//        System.out.println("............................... " + o.getClass().getName());
////        System.out.println("............................... " + document.getFields());
//
//    }
//}
