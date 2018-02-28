package model;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;


@Entity
@Table(name = "test")
@Indexed
@Spatial
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column()
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String name;

    @Column()
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private Integer var;

    @Longitude
    @Column()
    private double longitude;

    @Latitude
    @Column()
    private double latitude;

//    @Column()
//    private Point location;

    public TestEntity(double lon, double lat) {
        this.longitude = lon;
        this.latitude = lat;
    }

    public TestEntity() {
    }

    private Long getId() {
        return id;
    }
    private String getName() {
        return name;
    }
    private Integer getVar() {
        return var;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }
//    public Point getLocation() {
//        return this.location;
//    }

    public void setName(String name) {
        this.name = name;
    }
    public void setVar(Integer var) {
        this.var = var;
    }
    public void setLatitude(double lat) {
        this.latitude = lat;
    }
    public void setLongitude(double lon) {
        this.latitude = lon;
    }
//    public void setLocation(Point location) {
//        this.location = location;
//    }

//    public void addLocation(String wktPoint) {
//        Geometry geom = wktToGeometry(wktPoint);
//        setLocation((Point) geom);
//    }

//    private Geometry wktToGeometry(String wktPoint) {
//        WKTReader fromText = new WKTReader();
//        Geometry geom = null;
//        try {
//            geom = fromText.read(wktPoint);
//        } catch (ParseException e) {
//            throw new RuntimeException("Not a WKT string:" + wktPoint);
//        }
//        return geom;
//    }

    @Override
    public String toString() {
        return String.format("User id: %d, name: %s", getId(), getName());
    }
}