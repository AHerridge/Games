// package shops;
//
// import java.util.HashMap;
// import java.util.Map;
//
// import blocks.Block;
//
// public abstract class Shop< type >
// {
// private static Map catalog;
//
// public Shop()
// {
// catalog = new HashMap< type , Integer >();
// }
//
// public void addItem( type item )
// {
// catalog.putIfAbsent( item , ( int ) ( item.getPrice() + type.getPrice() *
// .25 ) );
// }
//
// public static void removeItem( Block block )
// {
// catalog.remove( block );
// }
//
// public static int getPrice( Block block )
// {
// return catalog.get( block );
// }
//
// public static void setPrice( Block block , int price )
// {
// catalog.put( block , price );
// }
//
// public static Block buy( Block block , int money )
// {
// if ( money - getPrice( block ) >= 0 )
// return block;
// return null;
// }
// }
