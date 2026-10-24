# Writes ch*/README.md from chapters.py. Run: python3 scripts/write_readmes.py
import os, glob, sys
sys.path.insert(0, os.path.dirname(__file__))
from chapters import CHAPTERS, PROJECTS
root = os.path.join(os.path.dirname(__file__), "..")
for n, (folder, title, subs) in enumerate(CHAPTERS, 1):
    dirs = sorted(d for d in os.listdir(os.path.join(root, folder)) if os.path.isdir(os.path.join(root, folder, d)))
    p_folder, p_title = PROJECTS[n - 1]
    lines = [f"# Chapter {n}: {title}", "", f"Code for Chapter {n} of *Java 199*. Each folder is one subtopic of the book.", "",
             "| # | Subtopic | Folder | How to run (from inside the folder) |", "|---|---|---|---|"]
    for i, (d, s) in enumerate(zip(dirs, subs), 1):
        path = os.path.join(root, folder, d)
        if os.path.exists(os.path.join(path, "run.sh")):
            how = "`bash run.sh`"
        else:
            src = sorted(glob.glob(os.path.join(path, "*.java")))[0]
            how = f"`java {os.path.basename(src)}`"
            if os.path.exists(os.path.join(path, "args.txt")):
                how = f"`java {os.path.basename(src)} {open(os.path.join(path,'args.txt')).read().strip()}`"
        lines.append(f"| {n}.{i} | {s} | [{d}]({d}/) | {how} |")
    lines += ["", f"`expected-output.txt` in each folder shows what you should see.", "",
              f"Chapter project: [{p_title}](../projects/{p_folder}/)", ""]
    open(os.path.join(root, folder, "README.md"), "w").write("\n".join(lines))
print("ok")
