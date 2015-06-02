package Entity;

/**
 * 
 * @author Domenic
 * 
 * The purpose of this class is solely to determine which sprite sheet should be loaded when a new material is given. Since it was very lengthy, I decided to make it its own class.
 *
 */

public class ArmorManager {

	private int location, material;
	
	private String spriteLocal;
	
	private static final int MATERIAL_TEST = 0;
	private static final int MATERIAL_NONE = 1;
	private static final int MATERIAL_LEATHER = 2;
	private static final int MATERIAL_COPPER = 3;
	private static final int MATERIAL_BRONZE = 4;
	private static final int MATERIAL_IRON = 5;
	private static final int MATERIAL_STEEL = 6;
	private static final int MATERIAL_TITANIUM = 7;
	
	public ArmorManager(int location, int material)
	{
		this.location = location;
		this.material = material;
	}
	
	public String getSpriteSheet()
	{
		switch(location)
		{
			
		case 1:
			if(material == MATERIAL_TEST)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.template.chestplate.png";
			}
			
			else if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.noarmor.chestplate.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.leather.chestplate.png";
			}
			
			else if(material == MATERIAL_COPPER)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.copper.chestplate.png";
			}
			
			else if(material == MATERIAL_BRONZE)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.bronze.chestplate.png";
			}
			
			else if(material == MATERIAL_IRON)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.iron.chestplate.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.steel.chestplate.png";
			}
			
			else if(material == MATERIAL_TITANIUM)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.titanium.chestplate.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.template.chestplate.png";
			}
			
			break;
			
		case 2:
			if(material == MATERIAL_TEST)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.template.gauntlets.png";
			}
			
			else if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.noarmor.gauntlets.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.leather.gauntlets.png";
			}
			
			else if(material == MATERIAL_COPPER)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.copper.gauntlets.png";
			}
			
			else if(material == MATERIAL_BRONZE)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.bronze.gauntlets.png";
			}
			
			else if(material == MATERIAL_IRON)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.iron.gauntlets.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.steel.gauntlets.png";
			}
			
			else if(material == MATERIAL_TITANIUM)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.titanium.gauntlets.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.template.gauntlets.png";
			}
			
			break;
			
		case 3:
			if(material == MATERIAL_TEST)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.template.leggings.png";
			}
			
			else if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.noarmor.leggings.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.leather.leggings.png";
			}
			
			else if(material == MATERIAL_COPPER)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.copper.leggings.png";
			}
			
			else if(material == MATERIAL_BRONZE)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.bronze.leggings.png";
			}
			
			else if(material == MATERIAL_IRON)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.iron.leggings.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.steel.leggings.png";
			}
			
			else if(material == MATERIAL_TITANIUM)
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.titanium.leggings.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.template.leggings.png";
			}
			
			break;
			
		case 4:
			if(material == MATERIAL_TEST)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.template.boots.png";
			}
			
			else if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.noarmor.boots.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.leather.boots.png";
			}
			
			else if(material == MATERIAL_COPPER)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.copper.boots.png";
			}
			
			else if(material == MATERIAL_BRONZE)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.bronze.boots.png";
			}
			
			else if(material == MATERIAL_IRON)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.iron.boots.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.steel.boots.png";
			}
			
			else if(material == MATERIAL_TITANIUM)
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.titanium.boots.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.template.boots.png";
			}
			
			break;
			
		case 5:
			if(material == MATERIAL_TEST)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.template.helmet.png";
			}
			
			else if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.noarmor.helmet.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.leather.helmet.png";
			}
			
			else if(material == MATERIAL_COPPER)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.copper.helmet.png";
			}
			
			else if(material == MATERIAL_BRONZE)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.bronze.helmet.png";
			}
			
			else if(material == MATERIAL_IRON)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.iron.helmet.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.steel.helmet.png";
			}
			
			else if(material == MATERIAL_TITANIUM)
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.titanium.helmet.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Helmet/hero.template.helmet.png";
			}
			
			break;
			
		default:
			
			spriteLocal = "/Sprites/Armor/nothing.png";
		}
		
		System.out.println(spriteLocal);
		
		return spriteLocal;
		
	}
	
	public void setMaterial(String material)
	{
		if(material.equals("test"))
		{
			this.material = MATERIAL_TEST;
		}
		
		else if(material.equals("none"))
		{
			this.material = MATERIAL_NONE;
		}
		
		else if(material.equals("leather"))
		{
			this.material = MATERIAL_LEATHER;		
		}
		
		else if(material.equals("copper"))
		{
			this.material = MATERIAL_COPPER;
		}
		
		else if(material.equals("bronze"))
		{
			this.material = MATERIAL_BRONZE;
		}
		
		else if(material.equals("iron"))
		{
			this.material = MATERIAL_IRON;
		}
		
		else if(material.equals("steel"))
		{
			this.material = MATERIAL_STEEL;
		}
		
		else if(material.equals("titanium"))
		{
			this.material = MATERIAL_TITANIUM;
		}
	}
	
}
