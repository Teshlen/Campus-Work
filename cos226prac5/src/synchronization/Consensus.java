package synchronization;

public interface Consensus<T>
{

    /**
     *
     * @param value
     * @return
     */
    T decide(T value);
}
