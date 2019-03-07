package org.mcupdater.randomworldname.compat.topography;

import com.bloodnbonesgaming.topography.client.gui.GuiButtonAlpha;
import com.bloodnbonesgaming.topography.client.gui.GuiCreateWorldTopography;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.apache.commons.lang3.StringUtils;
import org.mcupdater.randomworldname.RandomWorldName;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class CreateNewRandomTopographyWorld extends GuiCreateWorldTopography {
    public GuiButtonAlpha random;

    public CreateNewRandomTopographyWorld(GuiScreen guiScreen) {
        super(guiScreen);
    }

    @Override
    public void initGui() {
        super.initGui();

        random = this.addButton(new GuiButtonAlpha(11, 205, 0, 75, 20, "Randomize", 0.8F));
        random.visible = true;
        generateNewName();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 11) {
            generateNewName();
        }
    }

    private void generateNewName() {
        String newName = getRandomEntry(RandomWorldName.placeNames) + " of " + getRandomEntry(RandomWorldName.adjectives);
        this.worldNameField.setText(newName);
        this.worldName = newName;
        this.calcNewSaveDirName();
    }

    private String getRandomEntry(HashSet<String> sourceSet) {
        int size = sourceSet.size();
        int entry = new Random().nextInt(size);
        int i = 0;
        for (String name : sourceSet) {
            if (i == entry) {
                return name;
            }
            i++;
        }
        return "";
    }

    private void calcNewSaveDirName()
    {
        this.saveDirName = this.worldNameField.getText().trim();
        for (char c0 : ChatAllowedCharacters.ILLEGAL_FILE_CHARACTERS)
        {
            this.saveDirName = this.saveDirName.replace(c0, '_');
        }
        if (StringUtils.isEmpty(this.saveDirName))
        {
            this.saveDirName = "World";
        }
        this.saveDirName = getUncollidingSaveDirName(this.mc.getSaveLoader(), this.saveDirName);
    }

}
