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
	
	private static final int MATERIAL_NONE = 0;
	private static final int MATERIAL_LEATHER = 1;
	private static final int MATERIAL_STEEL = 2;
	
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
			if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/noarmorchestplate.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/leatherchestplate.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Chestplate/steelchestplate.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Chestplate/hero.template.chestplate.png";
			}
			
			break;
			
		case 2:
			if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/noarmorgauntlets.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/leathergauntlets.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/steelgauntlets.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Gauntlets/hero.template.gauntlets.png";
			}
			
			break;
			
		case 3:
			if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Leggings/noarmorleggings.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Leggings/leatherleggings.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Leggings/steelleggings.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Leggings/hero.template.leggings.png";
			}
			
			break;
			
		case 4:
			if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Boots/noarmorboots.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Boots/leatherboots.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Boots/steelboots.png";
			}
			
			else
			{
				spriteLocal = "/Sprites/Armor/Boots/hero.template.boots.png";
			}
			
			break;
			
		case 5:
			if(material == MATERIAL_NONE)
			{
				spriteLocal = "/Sprites/Armor/Helmet/noarmorhelmet.png";
			}
			
			else if(material == MATERIAL_LEATHER)
			{
				spriteLocal = "/Sprites/Armor/Helmet/leatherhelmet.png";
			}
			
			else if(material == MATERIAL_STEEL)
			{
				spriteLocal = "/Sprites/Armor/Helmet/steelhelmet.png";
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
	
	public void setMaterial(int material)
	{
		this.material = material;
	}
	
}
