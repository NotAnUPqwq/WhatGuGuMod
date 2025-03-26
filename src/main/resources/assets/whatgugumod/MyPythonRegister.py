allMaterialNeed = ('{0}_axe','{0}_pickaxe','{0}_spade','{0}_sword','{0}_hoe','{0}_helmet','{0}_chestplate','{0}_leggings','{0}_boots')
allMaterialNeedChinese = ('{0}斧','{0}镐','{0}锹','{0}剑','{0}锄','{0}头盔','{0}胸甲','{0}护腿','{0}靴子')
colorDictKeys = ('white','orange','magenta','lightblue','yellow','lime','pink','gray','silver','cyan','purple','blue','brown','green','red','black')
colorChinese = ('白色','橙色','品红色','淡蓝色','黄色','黄绿色','粉红色','灰色','淡灰色','青色','紫色','蓝色','棕色','绿色','红色','黑色')
def registry(myName:str,myType,myChineseName):
    myEnglishNameList = myName.replace('_spade','_shovel').split('_')
    myEnglishName = ''
    for i in myEnglishNameList:
        myList = list(i)
        myList[0] = chr(ord(myList[0])-32)
        for j in myList:
            myEnglishName += j
        myEnglishName += ' '
    myEnglishName.strip()
    if myType in ('item','handheld'):
        open(f'models\\item\\{myName}.json','w').write('''{
    "parent": "item/'''+('generated','handheld')[myType == 'handheld']+'''",
    "textures": {
       "layer0": "whatgugumod:items/'''+ myName +'''"
    }
}''')
        open(f'lang\\en_us.lang','a',encoding='UTF-8').write(f'item.{myName}.name={myEnglishName}\n'.replace('=Lightblue ','=LightBlue '))
        open(f'lang\\zh_cn.lang','a',encoding='UTF-8').write(f'item.{myName}.name={myChineseName}\n')
    elif myType == 'material':
        for i in range(len(allMaterialNeed)):
            if i <= 4:
                registry(allMaterialNeed[i].format(myName),'handheld',allMaterialNeedChinese[i].format(myChineseName))
            else:
                registry(allMaterialNeed[i].format(myName),'item',allMaterialNeedChinese[i].format(myChineseName))
    elif myType == 'color':
        for i in range(16):
            registry((colorDictKeys[i]+'_'+myName),'block',colorChinese[i]+myChineseName)
    else:
        open(f'models\\block\\{myName}.json','w').write('''{
    "parent": "block/cube_all",
    "textures": {
       "all": "whatgugumod:blocks/'''+ myName +'''"
    }
}''')
        
        open(f'models\\item\\{myName}.json','w').write('''{
    "parent": "whatgugumod:block/'''+ myName +'''"
}''')
        
        open(f'blockstates\\{myName}.json','w').write('''{
    "variants": {
        "normal": { "model": "whatgugumod:''' + myName +'''" }
    }
}''')
        open(f'lang\\en_us.lang','a',encoding='UTF-8').write(f'tile.{myName}.name={myEnglishName}\n')
        open(f'lang\\zh_cn.lang','a',encoding='UTF-8').write(f'tile.{myName}.name={myChineseName}\n')

if __name__ == '__main__':
    getName = input('请输入未本地化名：')
    while True:
        getType = input('请输入类型（item / block / material / color）：')
        if getType in ('item','block', 'material', 'color'): # 注：color目前仅支持方块
            break
        print('输入错误，请重新输入！')
    getChineseName = input('请输入中文名：')
    if getType in ('color',):
        getClass = input('请输入类名：')
    if input('输入yes开始生成文件，输入其他取消：') == 'yes' or input('再次输入yes确认生成文件，或输入其他取消：') == 'yes':
        registry(getName,getType,getChineseName)
        print('成功生成文件！\n复制以下代码：')
        if getType in ('item','block'):
            print(f'public static final {getType.capitalize()} {getName.upper()} = new {getType.capitalize()}Base("{getName}", {('Material.CLAY, ','')[getType=='item']}Main.GUGU_TAB);')
        elif getType == 'material':
            for i in range(len(allMaterialNeed)):
                if allMaterialNeed[i] == '{0}_shovel':
                    j = '{0}_spade'
                else:
                    j = allMaterialNeed[i]
                k = j.split('_')[1].capitalize()
                if i <= 4:
                    print(f'public static final Item{k} {j.format(getName).upper()} = new Tool{k}("{j.format(getName)}", Main.GUGU_TAB, MATERIAL_{getName.upper()});')
                else:
                    print(f'public static final Item {j.format(getName).upper()} = new ArmorBase("{j.format(getName)}", Main.GUGU_TAB, ARMOR_MATERIAL_{getName.upper()}, {(1,2)[i==7]}, EntityEquipmentSlot.{('HEAD','CHEST','LEGS','FEET')[i-5]});')
        else:
            for i in range(16):
                print(f'public static final Block {(colorDictKeys[i]+'_'+getName).upper()} = new {getClass}("{(colorDictKeys[i]+'_'+getName)}", Material.CLAY, Main.GUGU_TAB, {i});')
    else:
        print('文件生成取消！')