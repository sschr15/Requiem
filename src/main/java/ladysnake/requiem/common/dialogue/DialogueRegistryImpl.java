/*
 * Requiem
 * Copyright (C) 2017-2021 Ladysnake
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses>.
 *
 * Linking this mod statically or dynamically with other
 * modules is making a combined work based on this mod.
 * Thus, the terms and conditions of the GNU General Public License cover the whole combination.
 *
 * In addition, as a special exception, the copyright holders of
 * this mod give you permission to combine this mod
 * with free software programs or libraries that are released under the GNU LGPL
 * and with code included in the standard release of Minecraft under All Rights Reserved (or
 * modified versions of such code, with unchanged license).
 * You may copy and distribute such a system following the terms of the GNU GPL for this mod
 * and the licenses of the other code concerned.
 *
 * Note that people who make modified versions of this mod are not obligated to grant
 * this special exception for their modified versions; it is their choice whether to do so.
 * The GNU General Public License gives permission to release a modified version without this exception;
 * this exception also makes it possible to release a modified version which carries forward this exception.
 */
package ladysnake.requiem.common.dialogue;

import ladysnake.requiem.Requiem;
import ladysnake.requiem.api.v1.dialogue.CutsceneDialogue;
import ladysnake.requiem.api.v1.dialogue.DialogueAction;
import ladysnake.requiem.api.v1.dialogue.DialogueRegistry;
import ladysnake.requiem.common.RequiemRegistries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public final class DialogueRegistryImpl implements DialogueRegistry {
    private final Map<Identifier, DialogueAction> actions = new HashMap<>();

    @Override
    public CutsceneDialogue startDialogue(World world, Identifier id) {
        return new DialogueStateMachine(
            world.getRegistryManager().get(RequiemRegistries.DIALOGUES).getOrEmpty(id)
                .orElseThrow(() -> new IllegalArgumentException("Unknown dialogue " + id)),
            id
        );
    }

    @Override
    public void registerAction(Identifier actionId, DialogueAction action) {
        this.actions.put(actionId, action);
    }

    @Override
    public DialogueAction getAction(Identifier actionId) {
        if (!this.actions.containsKey(actionId)) {
            Requiem.LOGGER.warn("[Requiem] Unknown dialogue action {}", actionId);
            return DialogueAction.NONE;
        }
        return this.actions.get(actionId);
    }
}
