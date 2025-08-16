import os
#This is a helper for creating files. Not an actual part of the program
#usually in misc/helper
def fetch_file_names(suffix=None, prefix_I=False):
    current_file = os.path.basename(__file__)
    files = [f for f in os.listdir(".") if os.path.isfile(f) and f != current_file]
    files.sort()
    names = [os.path.splitext(f)[0] for f in files]
    if prefix_I:
        names = ["I" + name for name in names]
    if suffix:
        names = [name + suffix for name in names]
    return names

if __name__ == "__main__":
    print("\nChoose option:")
    print("1. Service")
    print("2. ServiceImpl")
    print("3. Repository")
    print("4. DTO")
    print("5. None")
    print("6. I + Service")
    print("7. I + ServiceImpl")
    print("8. I + Repository")
    print("9. I + DTO")
    print("10. I only (no suffix)")

    choice = input("Enter choice (1-10): ").strip()

    suffix_map = {
        "1": ("Service", False),
        "2": ("ServiceImpl", False),
        "3": ("Repository", False),
        "4": ("DTO", False),
        "5": (None, False),
        "6": ("Service", True),
        "7": ("ServiceImpl", True),
        "8": ("Repository", True),
        "9": ("DTO", True),
        "10": (None, True)
    }

    selected_suffix, prefix_I = suffix_map.get(choice, (None, False))

    file_names = fetch_file_names(selected_suffix, prefix_I)

    print("\n File names found:")
    for name in file_names:
        print(name)
