package ham_fisted;


import java.util.Map;
import java.util.function.Function;
import java.util.function.BiFunction;
import clojure.lang.IEditableCollection;
import clojure.lang.ITransientCollection;
import clojure.lang.IPersistentMap;
import clojure.lang.IObj;
import clojure.lang.IFn;

import static ham_fisted.BitmapTrieCommon.*;

public class ImmutBitmapTrie<K,V>
  extends NonEditableMapBase<K,V>
  implements IEditableCollection, IPersistentMap, IObj, ImmutValues,
	     BitmapTrieCommon.MapSet, BitmapTrie.BitmapTrieOwner {
  public ImmutBitmapTrie(HashProvider hp) {
    super(new BitmapTrie(hp));
  }
  public ImmutBitmapTrie(BitmapTrie ht) {
    super(ht);
  }
  public ImmutBitmapTrie<K,V> shallowClone() {
    return new ImmutBitmapTrie<K,V>((BitmapTrie)ht.shallowClone());
  }
  @SuppressWarnings("unchecked")
  public ImmutBitmapTrie<K,V> cons(Object obj) {
    return (ImmutBitmapTrie<K,V>)(shallowClone().mutConj(obj));
  }
  @SuppressWarnings("unchecked")
  public ImmutBitmapTrie<K,V> assoc(Object key, Object val) {
    return (ImmutBitmapTrie<K,V>)(shallowClone().mutAssoc((K)key, (V)val));
  }
  @SuppressWarnings("unchecked")
  public ImmutBitmapTrie<K,V> assocEx(Object key, Object val) {
    return (ImmutBitmapTrie<K,V>)(shallowClone().mutAssoc((K)key, (V)val));
  }
  @SuppressWarnings("unchecked")
  public ImmutBitmapTrie<K,V>  without(Object key) {
    return (ImmutBitmapTrie<K,V>)(shallowClone().mutDissoc((K)key));
  }
  public ImmutBitmapTrie<K,V> empty() {
    return new ImmutBitmapTrie<K,V>(ht.hashProvider());
  }
  public ITransientCollection asTransient() {
    if(isEmpty())
      return new MutBitmapTrie<K,V>((BitmapTrie)ht.shallowClone());
    else
      return new TransientBitmapTrie<K,V>((BitmapTrie)ht.shallowClone());
  }
  @SuppressWarnings("unchecked")
  public ImmutBitmapTrie<K,V> immutUpdateValues(BiFunction valueMap) {
    return (ImmutBitmapTrie<K,V>)(shallowClone().mutUpdateValues(valueMap));
  }
  @SuppressWarnings("unchecked")
  public ImmutBitmapTrie<K,V> immutUpdateValue(Object key, IFn fn) {
    return (ImmutBitmapTrie<K,V>)(shallowClone().mutUpdateValue((K)key, fn));
  }
  public BitmapTrie bitmapTrie() { return (BitmapTrie)ht; }
  public MapSet intersection(MapSet rhs, BiFunction valueMap) {
    BitmapTrie.BitmapTrieOwner owner = (BitmapTrie.BitmapTrieOwner)rhs;
    BitmapTrie rhsT = owner.bitmapTrie();
    return new ImmutBitmapTrie<K,V>(((BitmapTrie)ht).intersection(rhsT, valueMap));
  }
  public MapSet union(MapSet rhs, BiFunction valueMap) {
    BitmapTrie.BitmapTrieOwner owner = (BitmapTrie.BitmapTrieOwner)rhs;
    BitmapTrie rhsT = owner.bitmapTrie();
    return new ImmutBitmapTrie<K,V>(((BitmapTrie)ht).union(rhsT, valueMap));
  }
  public MapSet difference(MapSet rhs) {
    BitmapTrie.BitmapTrieOwner owner = (BitmapTrie.BitmapTrieOwner)rhs;
    BitmapTrie rhsT = owner.bitmapTrie();
    return new ImmutBitmapTrie<K,V>(((BitmapTrie)ht).difference(rhsT));
  }
  public ImmutBitmapTrie<K,V> withMeta(IPersistentMap m) {
    if(m == meta())
      return this;
    return new ImmutBitmapTrie<K,V>((BitmapTrie)ht.withMeta(m));
  }
}
