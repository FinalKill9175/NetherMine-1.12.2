package com.finalkg.nethermine;

import com.finalkg.nethermine.common.block.BlockNetherOre;
import com.finalkg.nethermine.common.entity.EntityArmedOre;
import com.finalkg.nethermine.common.event.UpdateOreSettingsOnDifficultyTickEvent;
import com.finalkg.nethermine.common.item.ItemBlockNetherOre;
import com.finalkg.nethermine.common.lib.EnumNetherOre;
import com.finalkg.nethermine.common.options.NetherMineOptionFile;
import com.finalkg.nethermine.common.proxy.CommonProxy;
import com.finalkg.nethermine.common.proxy.IC2Proxy;
import com.finalkg.nethermine.common.proxy.IIC2Proxy;
import com.finalkg.nethermine.common.proxy.NetherMineGuiHandler;
import com.finalkg.nethermine.common.proxy.NoIC2Proxy;
import com.finalkg.nethermine.common.world.gen.WorldGeneratorNetherOre;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMUpdateChecker;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = NetherMineCore.MODID, name = NetherMineCore.NAME, version = NetherMineCore.VERSION, dependencies = "required-after:wsbim@[1.12.2-1.3.0-r,);")
public class NetherMineCore {
	//Main information
		public static final String NAME = "Nether Mine";
		public static final String MODID = "nethermine";
		/**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
	    public static final int MASTER_BUILD_NUMBER = 1;
	    /**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
	    public static final int REVISION_NUMBER = 0;
	    /**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
	    public static final int BUILD_NUMBER = 1;
	    /**(a for alpha, b for beta, e for experimental, r for release)*/
	    public static final char BUILD_TYPE = 'r';
	    /**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
	    public static final String VERSION = "1.12.2-"+MASTER_BUILD_NUMBER+"."+REVISION_NUMBER+"."+BUILD_NUMBER+"-"+BUILD_TYPE;
	    //---------ESSENTIALS BEGIN-----------//
	    @Instance(MODID)
	    public static NetherMineCore instance;
	    public static org.apache.logging.log4j.Logger logger;
		public static IIC2Proxy ic2ModProxy; //Made to avoid hard references to IC2
	    /**WSBIM UPDATE CHECKER INSTANCE*/
	    public static WSBIMUpdateChecker updateChecker;
	    public static final NetherMineOptionFile options = new NetherMineOptionFile();
		@SidedProxy(clientSide = "com.finalkg.nethermine.client.proxy.ClientProxy", serverSide = "com.finalkg.nethermine.common.proxy.CommonProxy")
		public static CommonProxy proxy;
		//---------ESSENTIALS END-------------//
		//---------CONTENT BEGIN------------//
		public static Block[] blockNetherOres = new Block[(EnumNetherOre.values().length+15) / 16];
		public static ItemBlock[] itemBlockNetherOres = new ItemBlock[(EnumNetherOre.values().length+15) / 16];
		
		public static final CreativeTabs oreTab = new CreativeTabs(MODID) {
			long sysTime = -1L;
			ItemStack iconStack = ItemStack.EMPTY;
			final int maxBlockIndex = blockNetherOres.length;
			final int maxMetaIndex = 16;
			int blockIndex = 0;
			int metaIndex = 0;
			@Override @SideOnly(Side.CLIENT)
			public ItemStack getTabIconItem() {
				if(metaIndex >= maxMetaIndex) {
					metaIndex = 0;
					blockIndex++;
				}
				if(blockIndex >= maxBlockIndex) blockIndex = 0;
				return new ItemStack(blockNetherOres[blockIndex], 1, metaIndex++);
			}
		    @SideOnly(Side.CLIENT)
		    public ItemStack getIconItemStack(){
		    	if(sysTime == -1L || (System.currentTimeMillis() >= sysTime) || iconStack.isEmpty()) { //Cycle ores in creative tab.
		    		sysTime = System.currentTimeMillis() + 1000L;
		    		iconStack = this.getTabIconItem();
		    	}
		        return iconStack;
		    }
		};
		//---------CONTENT END--------------//
	    @EventHandler
	    public void preInit(FMLPreInitializationEvent e)
	    {
	        logger = e.getModLog();
	        //TODO
	        //updateChecker = new WSBIMUpdateChecker(MODID, NAME, VERSION, BUILD_TYPE, logger, "https://drive.google.com/uc?export=download&id=0BzMODdfxe3FUWlN4cnd2dkJsQ2s");
	        //WSBIM.registerUpdateChecker(MODID, updateChecker);
	        WSBIMOptions.registerCustomOptionFile(options);
	        int ent_id = 0;
	        EntityRegistry.registerModEntity(new ResourceLocation(MODID, "armedore"), EntityArmedOre.class, "armedore", ent_id++, instance, 80, 5, false);
	        if(Loader.isModLoaded("ic2")) this.ic2ModProxy = new IC2Proxy();
	        else this.ic2ModProxy = new NoIC2Proxy();
	    }
	    @EventHandler
	    public void init(FMLInitializationEvent e)
	    {
	        proxy.registerProxies();
	        NetworkRegistry.INSTANCE.registerGuiHandler(MODID, new NetherMineGuiHandler());
	        MinecraftForge.EVENT_BUS.register(new UpdateOreSettingsOnDifficultyTickEvent());
	        GameRegistry.registerWorldGenerator(new WorldGeneratorNetherOre(), 0);
	    }
	    @EventHandler
	    public void postInit(FMLPostInitializationEvent e) 
	    {
			if (!options.enableSmeltToOres) EnumNetherOre.COAL.registerSmelting(new ItemStack(Items.COAL));  // If not smelting to ore, smelt coal ore to coal.
			EnumNetherOre.COAL.setDropItem(new ItemStack(Items.COAL));
			if(options.getRegisterMaceratorRecipes()) ic2ModProxy.registerMaceratorRecipe(EnumNetherOre.COAL, new ItemStack(Items.COAL));
			for (EnumNetherOre ore : EnumNetherOre.values()) {
				String oreName;
				oreName = ore.getOreName(); // Ore
				if (options.enableSmeltToOres && OreDictionary.getOres(oreName).size() > 0)
					registerOreDictOre(ore, oreName, OreDictionary.getOres(oreName).get(0));
				oreName = ore.getSmeltName(); // Ingot
				if (OreDictionary.getOres(oreName).size() > 0)
					registerOreDictSmelt(ore, oreName, OreDictionary.getOres(oreName).get(0));
				oreName = ore.getDustName(); // Dust
				if (OreDictionary.getOres(oreName).size() > 0)
					registerOreDictDust(ore, oreName, OreDictionary.getOres(oreName).get(0));
				oreName = ore.getAltName(); // Gem
				if (OreDictionary.getOres(oreName).size() > 0)
					registerOreDictGem(ore, oreName, OreDictionary.getOres(oreName).get(0));
			}
			//MinecraftForge.EVENT_BUS.register(new OreDictionaryRegisterEvent()); Dont seem to actually need this...
	    }
	    
		public static  void registerOreDictSmelt(EnumNetherOre ore, String oreName, ItemStack stack){
			if (ore.getSmeltName().equals(oreName)) {
				ore.registerSmelting(stack);
				ore.setDropItem(stack);
			}
		}
		public static  void registerOreDictOre(EnumNetherOre ore, String oreName, ItemStack stack){
			if (!ore.isRegisteredSmelting() && ore.getOreName().equals(oreName))
				ore.registerSmelting(stack);
		}
		public static void registerOreDictDust(EnumNetherOre ore, String oreName, ItemStack stack){
			if (!ore.isRegisteredMacerator() && ore.getDustName().equals(oreName) && options.getRegisterMaceratorRecipes())
				ic2ModProxy.registerMaceratorRecipe(ore, stack);
		}

		public static  void registerOreDictGem(EnumNetherOre ore, String oreName, ItemStack stack){
			if (!ore.isRegisteredMacerator() && ore.getAltName().equals(oreName) && options.getRegisterMaceratorRecipes())
				ic2ModProxy.registerMaceratorRecipe(ore, stack);
		}
	    /**
	     * Responsible for all items and blocks being registered
	     * @author finalkg
	     *
	     */
	    @EventBusSubscriber(modid = MODID)
	    public static class RegistrationHandler{
	    	 	@SubscribeEvent
	    	    public static void registerItems(Register<Item> e) 
	    	    {
	    	    	//Item object creation
	    	    	//ie itemIronStick = new Item().setCreativeTab(CreativeTabs.MISC).setRegistryName(MODID, "ironStick").setUnlocalizedName("ironStick");
	    	    	//Item registration
	    	    	//ie e.getRegistry().register(itemIronStick);
	    	 		//Nether Ore ItemBlock creation
	    	 		for(int i = 0; i < itemBlockNetherOres.length; i++) {
	    	 			itemBlockNetherOres[i] = new ItemBlockNetherOre(i);
	    	 			itemBlockNetherOres[i].setRegistryName(blockNetherOres[i].getRegistryName());
	    	 			e.getRegistry().register(itemBlockNetherOres[i]);
	    	 		}
	    	    }
	    	 	
	    	    @SubscribeEvent
	    	    public static void registerBlocks(Register<Block> e)
	    	    {
	    	    	for(int i = 0; i < blockNetherOres.length; i++) {
	    	    		blockNetherOres[i] = new BlockNetherOre(i).setCreativeTab(oreTab);
	    	    		blockNetherOres[i].setRegistryName(NetherMineCore.MODID, "netherore"+i);
	    	    		blockNetherOres[i].setUnlocalizedName("netherore"+i);
	    	    		e.getRegistry().register(blockNetherOres[i]);
	    	    	}
	    	    }
	    }
}
