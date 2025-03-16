import json
import os

if __name__ == '__main__':
    block_path = "./src/main/resources/assets/kaleidoscope_doll/models/block/doll"
    item_path = "./src/main/resources/assets/kaleidoscope_doll/models/item"
    texture_path = "./src/main/resources/assets/kaleidoscope_doll/textures/block/doll"

    texture_prefix = "kaleidoscope_doll:block/doll/"

    correction_dict = {}

    # 读取 block 文件，获取修正映射表
    for block_file in os.listdir(block_path):
        block_json_file = block_path + "/" + block_file
        with open(block_json_file, "r", encoding='utf-8') as f:
            block_json_data = json.load(f)
            for (texture_id, texture) in block_json_data["textures"].items():
                if texture.startswith(texture_prefix):
                    texture_name = texture[len(texture_prefix):]
                    file_name = block_file[:len(block_file) - len(".json")]
                    correction_dict[file_name] = texture_name

    print(correction_dict)

    # 纠正 block json
    for (file_name, texture_name) in correction_dict.items():
        file_path = block_path + "/" + file_name + ".json"
        with open(file_path, "r", encoding='utf-8') as f:
            output_text = f.read().replace(texture_prefix + texture_name, texture_prefix + file_name)
        with open(file_path, "w", encoding='utf-8') as f:
            f.write(output_text)

    # 纠正 item json
    for (file_name, texture_name) in correction_dict.items():
        file_path = item_path + "/" + file_name + ".json"
        with open(file_path, "r", encoding='utf-8') as f:
            output_text = f.read().replace(texture_prefix + texture_name, texture_prefix + file_name)
        with open(file_path, "w", encoding='utf-8') as f:
            f.write(output_text)

    # 纠正材质名
    for (file_name, texture_name) in correction_dict.items():
        old_file_path = texture_path + "/" + texture_name + ".png"
        new_file_path = texture_path + "/" + file_name + ".png"
        if os.path.isfile(old_file_path):
            os.rename(old_file_path, new_file_path)
