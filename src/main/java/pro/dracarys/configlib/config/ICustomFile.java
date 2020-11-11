package pro.dracarys.configlib.config;

public interface ICustomFile<T> {

    T init();

    String getName();

}