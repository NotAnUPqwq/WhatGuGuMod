guguList = ['gugu_primogems',\
            'compressed_gugu_primogems']

for i in range(len(guguList)-1):
    with open(guguList[i+1]+".json",'w') as f:
        f.write('''{ 
    "type": "minecraft:crafting_shaped",
    
    "pattern":
    [
        "RRR",
        "RRR",
        "RRR"
    ],
    
    "key":
    {
        "R":
        {
            "item": "whatgugumod:'''+ guguList[i] +'''"
        }
    },
    
    "result":
    {
        "item": "whatgugumod:'''+ guguList[i+1] +'''",
        "count": 1
    }
}''')
    with open('decompress_'+guguList[i+1]+".json",'w') as f:
        f.write('''{ 
    "type": "minecraft:crafting_shapeless",
    
    "ingredients": 
    [
 
    {
      "item": "whatgugumod:'''+ guguList[i+1] +'''"
    }
 
    ],
 
    "result": 
    {
        "item": "whatgugumod:'''+ guguList[i] +'''",
        "count": 9
    }
}''')
print("运行完毕！")